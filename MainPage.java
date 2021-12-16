import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPage extends JFrame{

    private JList jList = createJList();
    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    JLabel passwordsLabel = new JLabel("Securi-Key", SwingConstants.CENTER);
    JButton viewPasswordButton = new JButton("View Password");
    JButton addPasswordButton = new JButton("Add Password");
    JButton editPasswordButton = new JButton("Edit Password");
    JButton deletePasswordButton = new JButton("Delete Password");


    public MainPage() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        initEvent();


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

        addPasswordButton.setBorderPainted(false);
        addPasswordButton.setFocusPainted(false);
        addPasswordButton.setBackground(offsetColor);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(addPasswordButton, c);

        editPasswordButton.setBorderPainted(false);
        editPasswordButton.setFocusPainted(false);
        editPasswordButton.setBackground(offsetColor);
        c.gridx = 2;
        c.gridy = 3;
        panel.add(editPasswordButton, c);

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
        list.setSelectionInterval(0, 0);
        list.setBackground(new Color(192, 192, 192));
        list.setVisibleRowCount(6);
        return list;
    }

    private ListModel<String> createDefaultListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 0; i < List.passwordsList.size(); i++) {
            String s = List.passwordsList.get(i).websiteName;
            model.addElement(s);
        }
        return model;
    }

    public void filterModel(DefaultListModel<String> model, String filter) {
        for (int i = 0; i < List.passwordsList.size(); i++) {
            String s = List.passwordsList.get(i).websiteName;
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

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
        viewPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedIndex() != -1) {
                    dispose();
                    int index = jList.getSelectedIndex();
                    new ViewPassword(index);
                }
            }
        });
        addPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddPassword();
            }
        });
        deletePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedIndex() != -1) {
                    dispose();
                    int index = jList.getSelectedIndex();
                    new DeleteConf(index);
                }
            }
        });
        editPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jList.getSelectedIndex() != -1) {
                    dispose();
                    int index = jList.getSelectedIndex();
                    new EditPassword(index);
                }
            }
        });
    }
    private static void process(String line) {
        System.out.println(line);
    }

    public static void errorHandle() {
        System.out.println("An error has occured.");
    }

    public static void readFile(){
        Pattern pattern = Pattern.compile("(.*)  -  (.*)");
        try {
            File myObj = new File("Passwords.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    List.passwordsList.add(new Password(matcher.group(1), matcher.group(2)));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainPage();
    }
}