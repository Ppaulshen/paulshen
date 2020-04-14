package view;

import bean.User;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Login window
 */
public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private static User loginUser;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setBounds(100, 100, 516, 401);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("SimSun", Font.PLAIN, 22));
        textField.setBounds(199, 67, 237, 50);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("SimSun", Font.PLAIN, 22));
        textField_1.setBounds(199, 178, 237, 50);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblName = new JLabel("name");
        lblName.setFont(new Font("SimSun", Font.PLAIN, 22));
        lblName.setBounds(45, 67, 109, 50);
        contentPane.add(lblName);

        JLabel lblPassword = new JLabel("password");
        lblPassword.setFont(new Font("SimSun", Font.PLAIN, 22));
        lblPassword.setBounds(45, 178, 109, 50);
        contentPane.add(lblPassword);

        JButton btnLogin = new JButton("login");
        btnLogin.setFont(new Font("SimSun", Font.PLAIN, 22));
        btnLogin.setBounds(295, 288, 141, 43);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(textField.getText());
                user.setPassword(textField_1.getText());
                if (login(user)) {
                    loginUser = user;
                    JOptionPane.showMessageDialog(null, "Successful logging");
                    WindowDesign.contentPane.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed");
                }
            }
        });
        contentPane.add(btnLogin);
    }

    public boolean login(User user) {
        try {
            Connection conn = MysqlUtil.getConnection();
            Statement statement = conn.createStatement();
            String sql = "select name,password from user";
            ResultSet rs = statement.executeQuery(sql);
            //Go to the database to find if there is a matching user
            while (rs.next()) {
                if (rs.getString("name").equals(user.getName())) {
                    if (rs.getString("password").equals(user.getPassword())) {
                        dispose();
                        return true;
                    }
                }
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getLoginUser() {
        return loginUser;
    }

    public static void setLoginUserNull() {
        loginUser = null;
    }



}
