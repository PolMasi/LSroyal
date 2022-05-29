package com.company.Presentation.Views;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
//http://codejavu.blogspot.com/2013/10/ejemplo-jtable.html
//http://www.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php
//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html

// FALTA PODER CLICKAR SOBRE UN USUARI, I QUE SORTI LA INFO DE LS PARTIDES GUARDADES.

/**
 * Mostra el ranking de les partides jugades per numero de victories
 */
public class RankingView extends JPanel {
    //private GridLayout gridBoard;
    private JPanel board;
    private JButton back ;
    private DefaultTableModel model;
    private String[][] data ;
    private JTable table1;
    private static final String titulos[] = { "Player", "Wins", "Average" };
    public static final String RANKING_BACK = "RANKING_BACK";

    /**
     * Contructor de la funció on es configura el panel
     */
    public RankingView() {

        configPanel(data, null);
    }

    /**
     * Actualizar el valor de la taula
     * @param table array doble de string per situar el valor de la taula correctament
     */
    private void updateTable(String[][] table) {

        data = table;

        model = new DefaultTableModel(data, titulos);
        table1 = new JTable(model){public boolean editCellAt(int row, int column, java.util.EventObject e) {
            return false;
        }
        };
        table1.getTableHeader().setToolTipText("Click to sort"); // si l'usuari posa el ratoli a sobre de la columna, li indica aixo
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table1.setRowSorter(sorter);
        model.fireTableDataChanged();
        table1.repaint();
    }

    /**
     * Cotrola un boto de sortida del panel
     * @param listener paramete per saber on estem
     */
    public void  registerController(ActionListener listener) {

        back.addActionListener(listener);
    }

    public void configPanel(String [][] matrix, ActionListener listener){

        this.removeAll();
        board = new JPanel(new BorderLayout());
        board.setForeground(Color.PINK);
        JPanel superiorPanel = new JPanel(new BorderLayout());
        superiorPanel.setForeground(Color.DARK_GRAY);

        JPanel auxiliarPanel = new JPanel(new BorderLayout());
        auxiliarPanel.setForeground(Color.DARK_GRAY);
        superiorPanel.add(auxiliarPanel, BorderLayout.WEST);

        JLabel title = new JLabel("RANKING",  SwingConstants.CENTER);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        superiorPanel.add(title, BorderLayout.CENTER);


        back = new JButton("BACK");
        back.setActionCommand(RANKING_BACK);
        superiorPanel.add(back, BorderLayout.EAST);
        board.add(superiorPanel, BorderLayout.NORTH);

        updateTable(matrix);

        JScrollPane table = new JScrollPane(table1);
        board.add(table, BorderLayout.CENTER);
        add(board);

         registerController(listener);




    }

}