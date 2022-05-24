package com.company.Presentation.Views;

import javax.swing.*;
//http://codejavu.blogspot.com/2013/10/ejemplo-jtable.html
public class RankingView extends JPanel {
    private JTable Table;
    String titulos[] = { "Player", "Win", "Average" };
    //String información[][] = obtieneMariz();// obtenemos la informacion de la BD
    /*public RankingView (){
        Table = new JTable(información, titulos);
        Table.setEnabled(false);
        Table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Table.setViewportView(Table);
    }
    private String[][] obtieneMariz() {
        PersonaDao miPersonaDao = new PersonaDao();
        /**
         * llamamos al metodo que retorna la info de la BD y la almacena en la
         * lista
         */
     //   ArrayList< PersonaVo > miLista = miPersonaDao.buscarUsuariosConMatriz();
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
