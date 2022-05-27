package com.company.Presentation.Views;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
//http://codejavu.blogspot.com/2013/10/ejemplo-jtable.html
//http://www.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php
//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html

// FALTA PODER CLICKAR SOBRE UN USUARI, I QUE SORTI LA INFO DE LS PARTIDES GUARDADES.
public class RankingView extends JFrame {
    //private GridLayout gridBoard;
    JPanel board;
    JButton back ;
    JTable table1;
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
        superiorPanel.add(back, BorderLayout.EAST);
        board.add(superiorPanel, BorderLayout.NORTH);

        String[][] data = {  //     prova per veure si funciona realment aixo ha d'anar conectat a la bbdd
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },{ "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" },
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Se acabo", "6014", "IT" }
        };
        // GERARD, HE POSAT AQUESTA STRING TAN LLARGA PER FER PROVES, REALMENT ON S'OBTE LA INFO DE LA BBDD ES EN EL METODE obtieneMariz()
        // String data[][] = obtieneMariz();
        String titulos[] = { "Player", "Wins", "Average" };
        TableModel model = new DefaultTableModel(data, titulos);
        table1 = new JTable(model);
        table1.getTableHeader().setToolTipText("Click to sort"); // si l'usuari posa el ratoli a sobre de la columna, li indica aixo
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table1.setRowSorter(sorter);

        JScrollPane table = new JScrollPane(table1);
        board.add(table, BorderLayout.CENTER);
        add(board);
    }
    // GERARD, AQUI HAS DE TREBALLAR TU, AMB LA BBDD, AQUEST EXEMPLE ESTA TRET D'UNA WEB QUE HE POSAT AL PRINCIPI

   /* private String[][] obtieneMariz() {
        PersonaDao miPersonaDao = new PersonaDao();*/
        /**
         * llamamos al metodo que retorna la info de la BD y la almacena en la
         * lista
         */
       // ArrayList< PersonaVo > miLista = miPersonaDao.buscarUsuariosConMatriz();
        /**
         * como sabemos que son 5 campos, definimos ese valor por defecto para
         * las columnas las filas dependen de los registros retornados
         */
       /* String informacion[][] = new String[miLista.size()][5];

        for (int x = 0; x < informacion.length; x++) {
            informacion[x][0] = miLista.get(x).getIdPersona() + "";
            informacion[x][1] = miLista.get(x).getNombrePersona() + "";
            informacion[x][2] = miLista.get(x).getProfesionPersona() + "";
            informacion[x][3] = miLista.get(x).getEdadPersona() + "";
            informacion[x][4] = miLista.get(x).getTelefonoPersona() + "";
        }
        return informacion;
    }*/
}