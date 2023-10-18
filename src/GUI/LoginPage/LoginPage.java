package GUI.LoginPage;

import Client.Client;
import GUI.RegistrationPage.RegistrationPage;
import Persistence.UserService.UserService;
import Persistence.UserService.UserServiceImp;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class LoginPage extends JFrame implements ActionListener {
    private final JFrame frame;
    private final Container loginContainer;
    private final JLabel title;
    private final JLabel usernameLabel;
    private final JTextField usernameValue;
    private final JLabel passwordLabel;
    private final JPasswordField passwordValue;
    private final JButton loginButton;
    private JLabel registrationPage;
    private JButton registrationPageButton;

    public LoginPage() {
        frame = new JFrame();
        frame.setTitle("HappyRoom");
        frame.setBounds(500, 100, 400, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginContainer = new Container();

        title = new JLabel("CONECTEAZA-TE");
        title.setBounds(145, 50, 115, 20);
        loginContainer.add(title);

        usernameLabel = new JLabel("Nume de utilizator:");
        usernameLabel.setBounds(50, 150, 115, 20);
        loginContainer.add(usernameLabel);

        usernameValue = new JTextField();
        usernameValue.setBounds(200, 150, 140, 20);
        loginContainer.add(usernameValue);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setBounds(50, 200, 115, 20);
        loginContainer.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setBounds(200, 200, 140, 20);
        loginContainer.add(passwordValue);

        loginButton = new JButton("CONECTARE");
        loginButton.setBounds(140, 300, 115, 40);
        loginButton.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            List<User> users = userService.read();
            boolean accountVerification = false;
            for(User userIterator : users) {
                if(userIterator.getUsername().compareTo(usernameValue.getText()) == 0 && userIterator.getPassword().compareTo(passwordValue.getText()) == 0) {
                    accountVerification = true;
                }
            }
            if(!accountVerification) {
                frame.setVisible(false);

                Socket socket;
                try {
                    socket = new Socket("localhost", 1234);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Client client = new Client(socket, usernameValue.getText());
                client.listenForMessage();
                client.sendMessage();
            }
        });
        loginContainer.add(loginButton);

        registrationPage = new JLabel("Nu ai cont?");
        registrationPage.setBounds(50, 385, 115, 20);
        loginContainer.add(registrationPage);

        registrationPageButton = new JButton("Inregistreaza-te");
        registrationPageButton.setBounds(120, 385, 130, 20);
        registrationPageButton.setBorder(BorderFactory.createEmptyBorder());
        registrationPageButton.setContentAreaFilled(false);
        registrationPageButton.addActionListener(e-> {
            new RegistrationPage();
            frame.setVisible(false);
        });
        loginContainer.add(registrationPageButton);

        frame.add(loginContainer);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
