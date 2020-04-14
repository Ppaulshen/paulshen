package view;

import bean.Book;
import bean.Order;
import bean.ShoppingCar;
import bean.User;

import dao.MysqlDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuyView extends JFrame {

    private JPanel contentPane;
    private JTextField userRealName;
    private JTextField userRealPhoneNumber;
    private JTextField userRealAddress;

    public BuyView(ShoppingCar car){
        setBounds(100, 100, 493, 433);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnBuyNow = new JButton("Buy now");
        btnBuyNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if(userRealName.getText().equals("")||userRealPhoneNumber.getText().equals("")||userRealAddress.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please fill in the information correctly");
                }else {
                    dispose();

                    UUID uuid = UUID.randomUUID();

                    StringBuilder stb = new StringBuilder();
                    List<Book> bookList = car.getBookList();
                    bookList.forEach(i->{
                        stb.append("<"+i.getName()+">,");
                    });


                    Order order = new Order();
                    order.setOrderUUID(uuid.toString());
                    order.setUserRealName(userRealName.getText());
                    order.setUserRealPhoneNumber(userRealPhoneNumber.getText());
                    order.setUserRealAddress(userRealAddress.getText());
                    order.setUserName(car.getUser().getName());
                    order.setOrderBooks(stb.toString());
                    order.setOrderStatus("this order is in transit");

                    System.out.println(order);

                    try {
                        new MysqlDao().orderInsert(order);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    WindowDesign.carBooks.clear();
                    OrderUUIDView orderUUIDView = new OrderUUIDView(uuid.toString());
                    orderUUIDView.setVisible(true);

                }
            }
        });
        btnBuyNow.setFont(new Font("SimSun", Font.PLAIN, 22));
        btnBuyNow.setBounds(319, 330, 125, 44);
        contentPane.add(btnBuyNow);

        JLabel lblSubmitOrder = new JLabel("submit order");
        lblSubmitOrder.setFont(new Font("SimSun", Font.BOLD, 28));
        lblSubmitOrder.setBounds(10, 7, 377, 50);
        contentPane.add(lblSubmitOrder);

        JLabel lblYourName = new JLabel("consignee");
        lblYourName.setFont(new Font("SimSun", Font.PLAIN, 22));
        lblYourName.setBounds(23, 114, 112, 26);
        contentPane.add(lblYourName);

        JLabel lblPhoneNumber = new JLabel("phone number");
        lblPhoneNumber.setFont(new Font("SimSun", Font.PLAIN, 23));
        lblPhoneNumber.setBounds(24, 180, 157, 26);
        contentPane.add(lblPhoneNumber);

        JLabel lblAddress = new JLabel("address");
        lblAddress.setFont(new Font("SimSun", Font.PLAIN, 23));
        lblAddress.setBounds(23, 234, 148, 44);
        contentPane.add(lblAddress);

        userRealName = new JTextField();
        userRealName.setFont(new Font("SimSun", Font.PLAIN, 23));
        userRealName.setColumns(20);
        userRealName.setBounds(211, 113, 232, 36);
        contentPane.add(userRealName);


        userRealPhoneNumber = new JTextField();
        userRealPhoneNumber.setFont(new Font("SimSun", Font.PLAIN, 23));
        userRealPhoneNumber.setColumns(20);
        userRealPhoneNumber.setBounds(211, 180, 232, 36);
        contentPane.add(userRealPhoneNumber);

        userRealAddress = new JTextField();
        userRealAddress.setFont(new Font("SimSun", Font.PLAIN, 23));
        userRealAddress.setColumns(20);
        userRealAddress.setBounds(211, 243, 232, 36);
        contentPane.add(userRealAddress);

        JLabel lblUser = new JLabel("user:"+car.getUser().getName());
        lblUser.setFont(new Font("SimSun", Font.PLAIN, 23));
        lblUser.setBounds(23, 44, 550, 44);
        contentPane.add(lblUser);

    }
}
