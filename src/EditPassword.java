import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EditPassword extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    private String websiteName;
    private String decryptedPassword;

    JButton cancelButton = new JButton("Cancel");
    JButton saveButton = new JButton("Save");
    JTextField newPasswordField = new JTextField(decryptedPassword,15);

    private int passwordIndex;

    public EditPassword(int passwordIndex) {

        this.passwordIndex = passwordIndex;
        this.websiteName = List.passwordsList.get(passwordIndex).websiteName;
        this.decryptedPassword = AES.decrypt(List.passwordsList.get(passwordIndex).passwordString, List.secretKey);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        initEvent();

        JLabel websiteNameLabel = new JLabel(websiteName, SwingConstants.CENTER);
        websiteNameLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(websiteNameLabel, c);

        newPasswordField.setText(decryptedPassword);
        newPasswordField.setBackground(offsetColor);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        panel.add(newPasswordField, c);

        cancelButton.setBorderPainted(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setBackground(offsetColor);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(cancelButton, c);

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

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List.passwordsList.set(passwordIndex, new Password(websiteName, AES.encrypt(newPasswordField.getText(), List.secretKey)));
                dispose();
                new MainPage();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainPage();
            }
        });
    }

    public static void main(String[] args) {
        //new EditPassword();
    }
}
