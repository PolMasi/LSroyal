package com.company.Presentation;

import com.company.Presentation.Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Configurem totes les vistes del videojoc per poder canviar en funcio de les voluntats de lusuari
 */
public class MainView extends JFrame {
    private LoginView loginView;
    private SignupView signupView;
    private StartView startView;
    private BoardView boardView;
    private MenuView menuView;
    private RankingView rankingView;
    private GameListView gameListView;

    private CardLayout cardLayout;      // gestionaremos como una baraja de cartas y este tendra todas las vista(cartas)

    public static final String LOGIN_VIEW = "LOGIN_VIEW";
    public static final String LOGOUT_VIEW = "LOGOUT_VIEW";
    public static final String SIGNUP_VIEW = "SIGNUP_VIEW";
    public static final String START_VIEW = "START_VIEW";
    public static final String BOARD_VIEW = "BOARD_VIEW";
    public static final String MENU_VIEW = "MENU_VIEW";
    public static final String RANKING_VIEW = "RANKING_VIEW";
    public static final String GAMELIST_VIEW = "GAMELIST_VIEW";


    public static final String TITLE = "LSROYAL";
    public static final String DELETE_MSG = "If you want to delete an account. Please enter your username:";

    /**
     * Constructor on inicialitzem totes les vistes
     * @param loginView vista de inicar sessio
     * @param signupView vista per registrarse
     * @param startView vista principal per recaudar dades de lusuari
     * @param boardView vista on es fa tota la logica del joc
     * @param menuView vista del menu principal del joc
     */
    public MainView(LoginView loginView, SignupView signupView, StartView startView, BoardView boardView, MenuView menuView, RankingView rankingView, GameListView gameListView) {
        this.startView = startView;
        this.loginView = loginView;
        this.signupView = signupView;
        this.boardView = boardView;
        this.menuView = menuView;
        this.rankingView = rankingView;
        this.gameListView = gameListView;

        configureLayout();
        configurationFrame();
    }

    private void configurationFrame() {
        pack();     //dejarlo compacto
        setTitle(TITLE);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();      //dimensiones de tu pantalla
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     //que pasara cuando cierres la pantalla //Acabar el programa
    }

    private void configureLayout() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);     //asignamos a esta ventana el cardlayout
        //this.add(MainView.BOARD_VIEW, boardView);
        //this.add(String.valueOf(CardEnum.MENU_VIEW), menuView);
        this.add(MainView.START_VIEW, startView);
        this.add(MainView.LOGIN_VIEW, loginView);    //le asginamos una vista con una frase y cuando le pasamos la frase pasa la vista asociada
        this.add(MainView.SIGNUP_VIEW, signupView);
        this.add(MainView.MENU_VIEW, menuView);
        this.add(MainView.BOARD_VIEW, boardView);
        this.add(MainView.RANKING_VIEW, rankingView);
        this.add(MainView.GAMELIST_VIEW, gameListView);
    }

    /**
     * Cambiar de vista
     * @param view String que identifica la vista que sha de canviar
     */
    public void switchView(String view) {
        cardLayout.show(getContentPane(),view);
        loginView.clear();
        signupView.clear();
    }

    /**
     * Assignem el mateix listener a totes les vistes
     * @param listener paramatre per saber on estem
     */
    public void setListeners(ActionListener listener){
        loginView.registerController(listener);
        startView.registerController(listener);
        signupView.registerController(listener);
        rankingView.registerController(listener);

    }

    /**
     * Assginem el listener a la taulell
     * @param listener paramatre per saber on estem
     */
    public void setBoardListener(ActionListener listener) {
        menuView.registerBoardController(listener);


    }

    public void setGameListListener(ActionListener listener){

        menuView.registerGameListController(listener);


    }



    /**
     * Mostrar un pop
     * @param text String on escriurem les instruccions
     * @param questions String amb les diferents opcions
     * @return retorna les dades introduides
     */
    public int showConfirmPopUp(String text, String[] questions) {
        return JOptionPane.showOptionDialog(this, text, null, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, questions, questions[0]);

    }

    /**
     * Mostrar un erorr
     * @param error String amb lerror cometes
     */
    public void showError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    /**
     * Mostrar un PopUp
     * @param text String del text que es vol informar
     * @return retorna una String amb el missatge
     */
    public String showInputPopUp(String text){

        String message = JOptionPane.showInputDialog(this, text);

        return message;
    }
}
