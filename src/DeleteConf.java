import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteConf extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    JButton yesButton = new JButton("Yes");
    JButton noButton = new JButton("No");

    private int passwordIndex;

    public DeleteConf(int passwordIndex) {
        this.passwordIndex = passwordIndex;
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        initEvent();

        JLabel passwordsLabel = new JLabel("Are you sure you want to delete this password?", SwingConstants.CENTER);
        passwordsLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordsLabel, c);

        yesButton.setBorderPainted(false);
        yesButton.setFocusPainted(false);
        yesButton.setBackground(offsetColor);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1;
        panel.add(yesButton, c);

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

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List.passwordsList.remove(passwordIndex);
                dispose();
                new MainPage();
            }
        });
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                dispose();
                new MainPage();
            }
        });
    }


    public static void main(String[] args) {
        //new DeleteConf();
    }
}
