import javax.swing.*;
import java.awt.*;

public class ViewPassword extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    private String websiteName = "randomwebsite.com";
    private String decryptedPassword = "abcdefg";

    public ViewPassword() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel websiteNameLabel = new JLabel(websiteName, SwingConstants.CENTER);
        websiteNameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(websiteNameLabel, c);

        JTextField field = new JTextField(decryptedPassword,15);
        field.setBackground(offsetColor);
        field.setEditable(false);
        c.insets = new Insets(10,10,10,10);
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(field, c);

        panel.setBackground(backColor);

        this.getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("View Password");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new ViewPassword();
    }
}