import javax.swing.*;
import java.awt.*;

public class EditPassword extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    private String websiteName = "randomwebsite.com";
    private String decryptedPassword = "abcdefg";


    public EditPassword() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel websiteNameLabel = new JLabel(websiteName, SwingConstants.CENTER);
        websiteNameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(websiteNameLabel, c);

        JTextField field = new JTextField(decryptedPassword,15);
        field.setBackground(offsetColor);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(field, c);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setBackground(offsetColor);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(cancelButton, c);

        JButton saveButton = new JButton("Save");
        saveButton.setBorderPainted(false);
        saveButton.setFocusPainted(false);
        saveButton.setBackground(offsetColor);
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(saveButton, c);

        panel.setBackground(backColor);

        this.getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Edit Password");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new EditPassword();
    }
}