import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MainPage extends JFrame{
    private String[] defaultValues =  {
            "amazon.com", "target.com", "clubpenguin.com", "coolmathgames.com", "khanacademy.org", "bankofamerica.com", "lego.com", "nike.com",
    };
    private JList jList = createJList();
    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);


    public MainPage() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel passwordsLabel = new JLabel("Securi-Key", SwingConstants.CENTER);
        passwordsLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordsLabel, c);

        c.ipady = 100;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JScrollPane(jList), c);

        JButton viewPasswordButton = new JButton("View Password");
        viewPasswordButton.setBorderPainted(false);
        viewPasswordButton.setFocusPainted(false);
        viewPasswordButton.setBackground(offsetColor);
        c.gridx = 0;
        c.gridy = 2;
        c.ipady = 0;
        panel.add(viewPasswordButton, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        panel.add(createTextField(), c);

        JButton addPasswordButton = new JButton("Add Password");
        addPasswordButton.setBorderPainted(false);
        addPasswordButton.setFocusPainted(false);
        addPasswordButton.setBackground(offsetColor);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(addPasswordButton, c);

        JButton editPasswordButton = new JButton("Edit Password");
        editPasswordButton.setBorderPainted(false);
        editPasswordButton.setFocusPainted(false);
        editPasswordButton.setBackground(offsetColor);
        c.gridx = 2;
        c.gridy = 3;
        panel.add(editPasswordButton, c);

        JButton deletePasswordButton = new JButton("Delete Password");
        deletePasswordButton.setBorderPainted(false);
        deletePasswordButton.setFocusPainted(false);
        deletePasswordButton.setBackground(offsetColor);
        c.gridx = 3;
        c.gridy = 3;
        panel.add(deletePasswordButton, c);

        panel.setBackground(backColor);

        this.getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Securi-Key Main");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JTextField createTextField() {
        final JTextField field = new JTextField(15);
        field.setBackground(new Color(192, 192, 192));
        field.getDocument().addDocumentListener(new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = field.getText();
                filterModel((DefaultListModel<String>)jList.getModel(), filter);
            }
        });
        return field;
    }

    private JList createJList() {
        JList list = new JList(createDefaultListModel());
        list.setBackground(new Color(192, 192, 192));
        list.setVisibleRowCount(6);
        return list;
    }

    private ListModel<String> createDefaultListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String s : defaultValues) {
            model.addElement(s);
        }
        return model;
    }

    public void filterModel(DefaultListModel<String> model, String filter) {
        for (String s : defaultValues) {
            if (!s.startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }
}
