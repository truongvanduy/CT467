import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JLabel lbWelcome;

    public Login() {
        setContentPane(mainPanel);
        setTitle("WELCOME");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameTextField.setText("");
                passwordTextField.setText("");
                lbWelcome.setText("");
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();


                if(username.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(mainPanel, "Some field are emty", "Error", 1);
                }

                MySQLConnect connect = new MySQLConnect();
                ResultSet resultSet = connect.queryStudent(username);
                try {
                    if(resultSet.next()) {
                        connect.writeResultSet(resultSet);
                        lbWelcome.setText(connect.getResultSetString(resultSet));
                    }
                    else {
                        System.out.println("Wrong ID");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        Login myFrame = new Login();
}}
