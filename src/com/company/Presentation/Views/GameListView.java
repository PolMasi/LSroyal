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

/**
 * Cotrol del panel de la lista de partides
 */
public class GameListView extends JPanel {

    private JPanel board;
    private JButton back ;
    private DefaultTableModel model;
    private String[][] data ;
    private JTable table1;
    private JScrollPane table;
    private Boolean selectedGame;
    private int gameID;
    private static final String titulos[] = {"ID","Name", "Date", "Win" };
    public static final String GAMELIST_BACK = "GAMELIST_BACK";

    /**
     * Contructor de la llista de partides
     */
    public GameListView (){

        configurePanel(data, null);

    }

    /**
     * Configuració del panel
     * @param dataM array doble per la informació de les patides
     * @param listener paramete per saber on estem
     */
    public void configurePanel(String [][] dataM, ActionListener listener){

        selectedGame = false;
        gameID = -1;

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

        updateTable(dataM);

        table = new JScrollPane(table1);
        board.add(table, BorderLayout.CENTER);
        add(board);
        registerController(listener);

    }

    /**
     * Actualitza la taula de dades
     * @param tableM array doble per la informació de les patides
     */
    private void updateTable(String[][] tableM) {

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
                    selectedGame = true;
                    gameID = Integer.parseInt((String) table1.getValueAt(row, 0));
                }
            }
        });
        table1.getTableHeader().setToolTipText("Click to sort"); // si l'usuari posa el ratoli a sobre de la columna, li indica aixo
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table1.setRowSorter(sorter);
        model.fireTableDataChanged();
        table1.repaint();

    }

    /**
     * Boto per sortir d'aquest panel
     * @param listener paramete per saber on estem
     */
    public void  registerController(ActionListener listener) {

        back.addActionListener(listener);
    }

    /**
     * Game ID
     * @return game id
     */

    public int getGameID() {
        return gameID;
    }

    /**
     * Obtener partida selecionada
     * @return partida
     */
    public Boolean getSelectedGame() {
        return selectedGame;
    }

    /**
     *
     * Partida selecionada
     * @param selectedGame partida correcta
     */

    public void setSelectedGame(Boolean selectedGame) {
        this.selectedGame = selectedGame;
    }
}
