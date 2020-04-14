package view;

import bean.User;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * register window
 */
public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblName;
    private JLabel lblPassword;


    /**
     * Create the frame.
     */
    public Register() {


        setBounds(100, 100, 518, 354);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("SimSun", Font.PLAIN, 22));
        textField.setBounds(218, 32, 253, 46);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("SimSun", Font.PLAIN, 22));
        textField_1.setBounds(218, 127, 253, 46);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton = new JButton("register");
        btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                user.setName(textField.getText());
                user.setPassword(textField_1.getText());
                try {
                    regist(user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(345, 231, 126, 52);
        contentPane.add(btnNewButton);

        lblName = new JLabel("name");
        lblName.setFont(new Font("SimSun", Font.PLAIN, 22));
        lblName.setBounds(33, 32, 111, 46);
        contentPane.add(lblName);

        lblPassword = new JLabel("password");
        lblPassword.setFont(new Font("SimSun", Font.PLAIN, 22));
        lblPassword.setBounds(33, 127, 111, 46);
        contentPane.add(lblPassword);
    }

    public void regist(User user) throws SQLException {
        //Prompt if username exists, otherwise registration is successful
        if (user != null) {
            Connection conn = MysqlUtil.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select name from user";
            ResultSet rs = stmt.executeQuery(sql);
            //Determine if it is already registered

            while (rs.next()) {
                if (rs.getString("name").equals(user.getName())) {
                    dispose();
                    JOptionPane.showMessageDialog(null, "User is already registered");
                    return;
                }
                if (rs.isLast()) {
                    if (!rs.getString("name").equals(user.getName())) {
                        //insert on doc
                        String addUser = "insert into user(name,password) values (?,?)";
                        PreparedStatement ptmt = conn.prepareStatement(addUser);
                        ptmt.setString(1, user.getName());
                        ptmt.setString(2, user.getPassword());
                        ptmt.execute();
                        dispose();
                        JOptionPane.showMessageDialog(null, "registration success");
                        return;
                    }
                }
            }
            //insert on doc
            String addUser = "insert into user(name,password) values (?,?)";
            PreparedStatement ptmt = conn.prepareStatement(addUser);
            ptmt.setString(1, user.getName());
            ptmt.setString(2, user.getPassword());
            ptmt.execute();
            dispose();
            JOptionPane.showMessageDialog(null, "registration success");
            return;
        }
    }
}
