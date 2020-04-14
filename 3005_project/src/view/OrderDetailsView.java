package view;

import bean.Order;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OrderDetailsView extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    public OrderDetailsView(Order order){
        setBounds(100, 100, 657, 569);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblOrderdetails = new JLabel("Order details");
        lblOrderdetails.setFont(new Font("SimSun", Font.BOLD, 23));
        lblOrderdetails.setBounds(10, 10, 266, 32);
        contentPane.add(lblOrderdetails);

        JLabel lblNewLabel = new JLabel("order number");
        lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 20));
        lblNewLabel.setBounds(20, 92, 140, 38);
        contentPane.add(lblNewLabel);

        JLabel lblUserrealname = new JLabel("consignee");
        lblUserrealname.setFont(new Font("SimSun", Font.BOLD, 20));
        lblUserrealname.setBounds(20, 158, 140, 38);
        contentPane.add(lblUserrealname);

        JLabel lblUserrealphonenumber = new JLabel("phone number");
        lblUserrealphonenumber.setFont(new Font("SimSun", Font.BOLD, 20));
        lblUserrealphonenumber.setBounds(20, 213, 192, 38);
        contentPane.add(lblUserrealphonenumber);

        JLabel lblUserrealaddress = new JLabel("address");
        lblUserrealaddress.setFont(new Font("SimSun", Font.BOLD, 20));
        lblUserrealaddress.setBounds(20, 270, 175, 38);
        contentPane.add(lblUserrealaddress);

        JLabel lblUsername = new JLabel("user name");
        lblUsername.setFont(new Font("SimSun", Font.BOLD, 20));
        lblUsername.setBounds(20, 332, 175, 38);
        contentPane.add(lblUsername);

        JLabel lblOrderstatus = new JLabel("order status");
        lblOrderstatus.setFont(new Font("SimSun", Font.BOLD, 20));
        lblOrderstatus.setBounds(20, 395, 175, 38);
        contentPane.add(lblOrderstatus);

        JLabel lblOrderbooks = new JLabel("order books");
        lblOrderbooks.setFont(new Font("SimSun", Font.BOLD, 20));
        lblOrderbooks.setBounds(20, 454, 175, 38);
        contentPane.add(lblOrderbooks);

        textField = new JTextField();
        textField.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField.setBounds(210, 95, 386, 32);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_1.setColumns(10);
        textField_1.setBounds(210, 161, 386, 32);
        contentPane.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_2.setColumns(10);
        textField_2.setBounds(210, 216, 386, 32);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_3.setColumns(10);
        textField_3.setBounds(210, 273, 386, 32);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_4.setColumns(10);
        textField_4.setBounds(210, 335, 386, 32);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_5.setColumns(10);
        textField_5.setBounds(210, 398, 386, 32);
        contentPane.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("SimSun", Font.PLAIN, 19));
        textField_6.setColumns(10);
        textField_6.setBounds(210, 457, 386, 32);
        contentPane.add(textField_6);


        textField.setText(order.getOrderUUID());
        textField_1.setText(order.getUserRealName());
        textField_2.setText(order.getUserRealPhoneNumber());
        textField_3.setText(order.getUserRealAddress());
        textField_4.setText(order.getUserName());
        textField_5.setText(order.getOrderStatus());
        String orderBooks = order.getOrderBooks();
        String substring = "";
        if(orderBooks.length()>=1){
           substring = orderBooks.substring(0, orderBooks.length() - 1);
        }
        
        textField_6.setText(substring);
    }

}
