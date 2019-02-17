package src.java;

import src.java.objects.User;
import src.java.modules.UserDB;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App extends JFrame {
    final static long serialVersionUID = 0;

    private static final int width = 1200;
    private static final int height = 800;

    private JButton closeButton = new JButton();
    
    private JPanel logInPanel = new JPanel();
    private JLabel titleText = new JLabel("FRESH");
    private JTextField usernameField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JButton logInButton = new JButton("Log in");
    private JButton registerButton = new JButton("Register");
    private JLabel feedbackText = new JLabel();

    private JPanel loggedPanel = new JPanel();
    private JLabel loggedText = new JLabel("You are logged in as ...");
    private JButton returnButton = new JButton();

    private UserDB userDB = new UserDB();

    private User currentUser;

    private BufferedImage closeButtonIcon;
    private BufferedImage returnButtonIcon;

    public App() {
        // Load external resources
        try {
            closeButtonIcon = ImageIO.read(App.class.getResourceAsStream("/src/resources/close64x64.png"));
            closeButton.setIcon(new ImageIcon(closeButtonIcon));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            returnButtonIcon = ImageIO.read(App.class.getResourceAsStream("/src/resources/return64x64.png"));
            returnButton.setIcon(new ImageIcon(returnButtonIcon));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // Set up the window        
        setTitle("Fresh");
        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);

        closeButton.setBounds(1126, 10, 64, 64);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(closeButton);

        setVisible(true);        

        // Set up the log in panel
        logInPanel.setBounds(0, 0, width, height);
        logInPanel.setOpaque(false);
        logInPanel.setLayout(null);
        logInPanel.setVisible(false);
        add(logInPanel);

        titleText.setBounds(100, 100, 1000, 100);
        titleText.setFont(new Font("Helvetica", Font.BOLD, 80));
        titleText.setVerticalAlignment(SwingConstants.TOP);
        titleText.setHorizontalAlignment(SwingConstants.CENTER);
        logInPanel.add(titleText);

        usernameField.setBounds(450, 300, 300, 50);
        usernameField.setFont(new Font("Helvetica", Font.BOLD, 30));
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        logInPanel.add(usernameField);

        passwordField = new JTextField();
        passwordField.setBounds(450, 375, 300, 50);
        passwordField.setFont(new Font("Helvetica", Font.BOLD, 30));
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        logInPanel.add(passwordField);

        logInButton.setBounds(450, 450, 300, 50);
        logInButton.setFont(new Font("Helvetica", Font.BOLD, 30));
        logInButton.setVerticalAlignment(SwingConstants.CENTER);
        logInButton.setHorizontalAlignment(SwingConstants.CENTER);
        logInButton.setBorderPainted(false);
        logInButton.setFocusPainted(false);
        logInButton.setBackground(Color.darkGray);
        logInButton.setForeground(Color.white);
        logInButton.addActionListener(new ActionListener(){        
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    feedbackText.setText("Both fields are required.");
                    return;
                }

                User user = new User(usernameField.getText(), passwordField.getText());
                int status = userDB.checkUser(user);
                if (status == 0) {                   
                    logInPanel.setVisible(false);
                    feedbackText.setText("");
                    currentUser = user;
                    loggedText.setText("You are logged in as " + currentUser.getUsername());
                    loggedPanel.setVisible(true);
                } else if (status == -1) {
                    feedbackText.setText("The username doesn't exist.");
                } else {
                    feedbackText.setText("The password isn't correct.");
                }
            }
        });
        logInPanel.add(logInButton);

        registerButton.setBounds(450, 525, 300, 50);
        registerButton.setFont(new Font("Helvetica", Font.BOLD, 30));
        registerButton.setVerticalAlignment(SwingConstants.CENTER);
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(Color.darkGray);
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                    feedbackText.setText("Both fields are required.");
                    return;
                }
                
                User user = new User(usernameField.getText(), passwordField.getText());
                if (userDB.addUser(user) == -1) {
                    feedbackText.setText("That user already exists.");
                } else {
                    feedbackText.setText("");
                }
            }
        });
        logInPanel.add(registerButton);

        feedbackText.setBounds(450, 600, 300, 50);
        feedbackText.setFont(new Font("Helvetica", Font.BOLD, 18));
        feedbackText.setForeground(Color.red);
        feedbackText.setVerticalAlignment(SwingConstants.TOP);
        feedbackText.setHorizontalAlignment(SwingConstants.CENTER);
        logInPanel.add(feedbackText);

        // Set up the app panel
        loggedPanel.setBounds(0, 0, width, height);
        loggedPanel.setOpaque(false);
        loggedPanel.setLayout(null);
        loggedPanel.setVisible(false);
        add(loggedPanel);

        loggedText.setBounds(100, 300, 1000, 100);
        loggedText.setFont(new Font("Helvetica", Font.BOLD, 50));
        loggedText.setVerticalAlignment(SwingConstants.TOP);
        loggedText.setHorizontalAlignment(SwingConstants.CENTER);
        loggedPanel.add(loggedText);

        returnButton.setBounds(10, 10, 64, 64);
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        returnButton.addActionListener(new ActionListener() {        
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedPanel.setVisible(false);
                logInPanel.setVisible(true);
            }
        });
        loggedPanel.add(returnButton);        

        // Set initial panel visible
        logInPanel.setVisible(true);
    }
}