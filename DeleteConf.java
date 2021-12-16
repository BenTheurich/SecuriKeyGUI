import javax.swing.*;
import java.awt.*;

public class DeleteConf extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);


    public DeleteConf() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel passwordsLabel = new JLabel("Are you sure you want to delete this password?", SwingConstants.CENTER);
        passwordsLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordsLabel, c);

        JButton yesButton = new JButton("Yes");
        yesButton.setBorderPainted(false);
        yesButton.setFocusPainted(false);
        yesButton.setBackground(offsetColor);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        panel.add(yesButton, c);

        JButton noButton = new JButton("No");
        noButton.setBorderPainted(false);
        noButton.setFocusPainted(false);
        noButton.setBackground(offsetColor);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(noButton, c);

        panel.setBackground(backColor);

        this.getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Delete Password Confirmation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new DeleteConf();
    }
}