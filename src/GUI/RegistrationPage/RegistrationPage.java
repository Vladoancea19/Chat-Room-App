package GUI.RegistrationPage;

import GUI.LoginPage.LoginPage;
import Persistence.UserService.UserService;
import Persistence.UserService.UserServiceImp;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

public class RegistrationPage extends JFrame implements ActionListener {
    private final JFrame frame;
    private final Container registrationContainer;
    private final JLabel title;
    private final JLabel usernameLabel;
    private final JTextField usernameValue;
    private final JLabel passwordLabel;
    private final JPasswordField passwordValue;
    private final JButton registrationButton;
    private JLabel loginPage;
    private JButton loginPageButton;

    public RegistrationPage() {
        frame = new JFrame();
        frame.setTitle("HappyRoom");
        frame.setBounds(500, 100, 400, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registrationContainer = new Container();

        title = new JLabel("CREEAZA CONT");
        title.setBounds(145, 50, 115, 20);
        registrationContainer.add(title);

        usernameLabel = new JLabel("Nume de utilizator:");
        usernameLabel.setBounds(50, 150, 115, 20);
        registrationContainer.add(usernameLabel);

        usernameValue = new JTextField();
        usernameValue.setBounds(200, 150, 140, 20);
        registrationContainer.add(usernameValue);

        passwordLabel = new JLabel("Parola:");
        passwordLabel.setBounds(50, 200, 115, 20);
        registrationContainer.add(passwordLabel);

        passwordValue = new JPasswordField();
        passwordValue.setBounds(200, 200, 140, 20);
        registrationContainer.add(passwordValue);

        registrationButton = new JButton("CREARE CONT");
        registrationButton.setBounds(130, 300, 125, 40);
        registrationButton.addActionListener(e -> {
            UserService userService = new UserServiceImp();
            List<User> users = userService.read();
            boolean usernameVerification = false;
            for(User userIterator : users) {
                if(userIterator.getUsername().compareTo(usernameValue.getText()) == 0) {
                    usernameVerification = true;
                }
            }
            if(!usernameValue.getText().isEmpty() && !passwordValue.getText().isEmpty()) {
                if(!usernameVerification) {
                    User user = new User(usernameValue.getText(), passwordValue.getText());
                    userService.create(user);
                    new LoginPage();
                    frame.setVisible(false);
                }
            }
        });
        registrationContainer.add(registrationButton);

        loginPage = new JLabel("Ai deja cont?");
        loginPage.setBounds(50, 385, 115, 20);
        registrationContainer.add(loginPage);

        loginPageButton = new JButton("Conecteaza-te");
        loginPageButton.setBounds(120, 385, 130, 20);
        loginPageButton.setBorder(BorderFactory.createEmptyBorder());
        loginPageButton.setContentAreaFilled(false);
        loginPageButton.addActionListener(e-> {
            new LoginPage();
            frame.setVisible(false);
        });
        registrationContainer.add(loginPageButton);

        frame.add(registrationContainer);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
