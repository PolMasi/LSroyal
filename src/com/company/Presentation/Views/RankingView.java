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

        JLabel refresh = new JLabel("Refresh and reorder statistics according to the pressed column!",  SwingConstants.CENTER);
        refresh.setForeground(Color.RED);
        refresh.setFont(new Font("Helvetica", Font.PLAIN, 15));
        superiorPanel.add(refresh, BorderLayout.SOUTH);

        back = new JButton("BACK");
        back.setActionCommand(RANKING_BACK);
        superiorPanel.add(back, BorderLayout.EAST);
        board.add(superiorPanel, BorderLayout.NORTH);


        // GERARD, HE POSAT AQUESTA STRING TAN LLARGA PER FER PROVES, REALMENT ON S'OBTE LA INFO DE LA BBDD ES EN EL METODE obtieneMariz()
        // String data[][] = obtieneMariz();
        model = new DefaultTableModel(data, titulos);
        table1 = new JTable(model);
        table1.getTableHeader().setToolTipText("Click to sort"); // si l'usuari posa el ratoli a sobre de la columna, li indica aixo
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table1.setRowSorter(sorter);

        JScrollPane table = new JScrollPane(table1);
        board.add(table, BorderLayout.CENTER);
        add(board);
    }

    /**
     * Actualizar el valor de la taula
     * @param table array doble de string per situar el valor de la taula correctament
     */
    public void updateTable(String[][] table) {

        model = new DefaultTableModel(table, titulos);
        table1 = new JTable(model);
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

}