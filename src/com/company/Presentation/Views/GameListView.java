package com.company.Presentation.Views;

import com.company.Persistence.GameDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameListView extends JPanel {

    private JPanel board;
    private JButton back ;
    private DefaultTableModel model;
    private String[][] data ;
    private JTable table1;
    private JScrollPane table;
    private static final String titulos[] = {"ID","Name", "Date", "Win" };
    public static final String GAMELIST_BACK = "GAMELIST_BACK";


    public GameListView (){

        configurePanel(data, null);

    }

    public void configurePanel(String [][] dataM, ActionListener listener){

        this.removeAll();
        board = new JPanel(new BorderLayout());
        board.setForeground(Color.PINK);
        JPanel superiorPanel = new JPanel(new BorderLayout());
        superiorPanel.setForeground(Color.DARK_GRAY);

        JPanel auxiliarPanel = new JPanel(new BorderLayout());
        auxiliarPanel.setForeground(Color.DARK_GRAY);
        superiorPanel.add(auxiliarPanel, BorderLayout.WEST);

        JLabel title = new JLabel("GAMES",  SwingConstants.CENTER);
        title.setForeground(Color.YELLOW);
        title.setFont(new Font("Helvetica", Font.BOLD, 50));
        superiorPanel.add(title, BorderLayout.CENTER);


        back = new JButton("BACK");
        back.setActionCommand(GAMELIST_BACK);
        superiorPanel.add(back, BorderLayout.EAST);
        board.add(superiorPanel, BorderLayout.NORTH);


        // GERARD, HE POSAT AQUESTA STRING TAN LLARGA PER FER PROVES, REALMENT ON S'OBTE LA INFO DE LA BBDD ES EN EL METODE obtieneMariz()
        // String data[][] = obtieneMariz();

        updateTable(dataM);

        table = new JScrollPane(table1);
        board.add(table, BorderLayout.CENTER);
        add(board);
        registerController(listener);



    }

    public void updateTable(String[][] tableM) {

        data = tableM;
        model = new DefaultTableModel(data, titulos);
        table1 = new JTable(model){

            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }

        };

        table1.setFocusable(false);
        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column
                    JOptionPane.showMessageDialog(null, table1.getValueAt(row, column)); // get the value of a row and column.
                }
            }
        });
        table1.getTableHeader().setToolTipText("Click to sort"); // si l'usuari posa el ratoli a sobre de la columna, li indica aixo
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table1.setRowSorter(sorter);
        model.fireTableDataChanged();
        table1.repaint();

    }

    public void  registerController(ActionListener listener) {

        back.addActionListener(listener);
    }
}
