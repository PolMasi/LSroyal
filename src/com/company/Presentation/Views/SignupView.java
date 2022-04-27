import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;


public class SignupView extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;
    private JTextField emailText;
    private JPasswordField passwordConfirmText;
    private JButton signUp;
    private JButton goBack;

    public SignupView (){
        super("Sign up Form");

        //dimensiones de tu pantalla
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();     
        setSize(size.width, size.height);
        setLocationRelativeTo(null);    //para centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Buttons
        signUp = new JButton("Sign up");
        goBack = new JButton("Go back");
        
        //JLabel 
        JLabel title = new JLabel("REGISTER FORM",SwingConstants.CENTER);
        JLabel name = new JLabel("Name", SwingConstants.RIGHT);
        JLabel email  = new JLabel("Email", SwingConstants.RIGHT);
        JLabel passwordConfirm = new JLabel("Password Confirmation", SwingConstants.RIGHT);
        JLabel password = new JLabel("Password", SwingConstants.RIGHT);
        
        // font de las etiquetas y buttons
        title.setFont(new Font("Helvetica", Font.BOLD, 100));
        title.setForeground(Color.WHITE);

        name.setFont(new Font("Helvetica", Font.PLAIN, 20));
        email.setFont(new Font("Helvetica", Font.PLAIN, 20));
        passwordConfirm.setFont(new Font("Helvetica", Font.PLAIN, 20));
        password.setFont(new Font("Helvetica", Font.PLAIN, 20));

        goBack.setFont(new Font("Helvetica", Font.PLAIN, 20));
        signUp.setFont(new Font("Helvetica", Font.BOLD, 20));

        //text introduced
        userText = new JTextField(20);
        emailText = new JTextField(20);
        passwordText = new JPasswordField(20);
        passwordConfirmText = new JPasswordField(20);
        
        // recuadro grande, toda la pantalla
        Container mainContainer = this.getContentPane();
        mainContainer.setBackground(Color.DARK_GRAY);
        
        //bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.DARK_GRAY);
        bottomPanel.add(signUp);
        bottomPanel.add(goBack);

        // central panel
        JPanel gridJPanel = new JPanel();
        gridJPanel.setLayout(new GridLayout(4,2,40,120));
        gridJPanel.setBorder(new LineBorder(Color.BLACK, 3));
        gridJPanel.setBackground(Color.LIGHT_GRAY);

        //name
        gridJPanel.add(name);
        gridJPanel.add(userText);

        //email
        gridJPanel.add(email);
        gridJPanel.add(emailText);

        //password
        gridJPanel.add(password);
        gridJPanel.add(passwordText);
        
        //password confirmation
        gridJPanel.add(passwordConfirm);
        gridJPanel.add(passwordConfirmText);
        
        gridJPanel.setBorder(new LineBorder(Color.BLACK, 10));

        //posicion 
        mainContainer.add(title, BorderLayout.NORTH);
        mainContainer.add(bottomPanel, BorderLayout.SOUTH);
        mainContainer.add(gridJPanel, BorderLayout.CENTER);

    }
   
    public String getUser(){
        return userText.getText();
    }
    public String getEmail(){
        return emailText.getText();
    }
    public String getPassword(){
        return passwordText.getText();
    }
    public String getPasswordConfirm(){
        return passwordConfirmText.getText();
    }

    public static void main(String[] args){
        SignupView view = new SignupView();
        view.setVisible(true);
    }
}