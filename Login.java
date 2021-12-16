import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends JFrame{

    private Color backColor = new Color(147, 147, 147);
    private Color offsetColor = new Color(192, 192, 192);

    JButton loginButton = new JButton("Login");
    JPasswordField passwordField = new JPasswordField(15);

    int failed = 0;

    public Login() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        initEvent();

        JLabel passwordsLabel = new JLabel("Enter Your Password", SwingConstants.CENTER);
        passwordsLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.insets = new Insets(5,5,5,5);
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(passwordsLabel, c);

        passwordField.setEchoChar('*');
        passwordField.setBackground(offsetColor);
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.VERTICAL;
        panel.add(passwordField, c);

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


    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input  = String. valueOf(passwordField.getPassword());

                if (failed <= 4){
                    if (Integer.toString(input.hashCode()).equals(returnPass())) {
                        dispose();
                        new MainPage();
                        System.out.println(failed);
                    } else {
                        System.out.println(failed);
                        ++failed;
                        JOptionPane.showMessageDialog(passwordField,
                                "Invalid password. Try again.",
                                "Error Message",
                                JOptionPane.ERROR_MESSAGE);

                    }

                    passwordField.selectAll();
                }else{
                    File deleteFile = new File("Passwords.txt");
                    deleteFile.delete();
                    System.out.println("File Deleted.");
                }
            }

        });
    }


    public static void errorHandle() {
        System.out.println("An error has occured.");
    }


    public static boolean doesFileExist(String info) {
        try {
            File myObj = new File(info);
            if (myObj.createNewFile()) {
                return false;
            } else {
                return true;
            }
        } catch(IOException e) {
            errorHandle();
            e.printStackTrace();
        }
        return false;

    }

    public static void makeFile(String info) {
        try {
            File myObj = new File(info);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch(IOException e) {
            errorHandle();
            e.printStackTrace();
        }
    }

    public static void firstTimeUser(){

        if (doesFileExist("User.txt") != true){

            String pass = JOptionPane.showInputDialog("Please create a password.");
            Scanner user = new Scanner(System.in);
            System.out.println("Please create your password.");

            //String pass = user.nextLine();
            pass = Integer.toString(pass.hashCode());
            makeFile("User.txt");
            WriteToFile("User.txt", pass);
        }
    }
    public static void WriteToFile(String info, String data) {
        try {
            FileWriter myWriter = new FileWriter(info);
            myWriter.write(data);
            myWriter.close();

        } catch (IOException e)
        {
            errorHandle();
            e.printStackTrace();
        }
    }
    public String returnPass(){
        String pass = new String();
        try {
            File myObj = new File("User.txt");
            Scanner myReader = new Scanner(myObj);

            pass = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return pass;
    }

    public static void WriteToFile(String info, String data, boolean append) {
        try {
            FileWriter myWriter = new FileWriter(info, append);
            myWriter.write(data);
            System.lineSeparator();
            myWriter.close();

        } catch (IOException e) {
            errorHandle();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        firstTimeUser();
        new Login();
        MainPage.readFile();
        //Save current passwords to Passwords.txt
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                WriteToFile("Passwords.txt", "", false);
                for (int i = 0; i < List.passwordsList.size(); i++) {
                    String input = (List.passwordsList.get(i).websiteName+ "  -  " + List.passwordsList.get(i).passwordString + "\n");
                    WriteToFile("Passwords.txt", input, true);
                }
            }
        }));
    }
}