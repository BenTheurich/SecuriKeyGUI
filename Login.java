import javax.swing.*;
import java.awt.*;

public class Login extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);


    public Login() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel passwordsLabel = new JLabel("Enter Your Password", SwingConstants.CENTER);
        passwordsLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordsLabel, c);

        JPasswordField field = new JPasswordField(15);
        field.setEchoChar('*');
        field.setBackground(offsetColor);
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        panel.add(field, c);

        JButton loginButton = new JButton("Login");
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(offsetColor);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(loginButton, c);

        panel.setBackground(backColor);

        this.getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Securi-Key Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Login();
    }
}
