package view;

import bean.Order;
import dao.MysqlDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckView extends JFrame{

    private JPanel contentPane;
    private JTextField textField;

    public CheckView(){
        setBounds(100, 100, 493, 187);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(55, 75, 300, 36);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton check = new JButton("check");
        check.setFont(new Font("SimSun", Font.PLAIN, 20));
        check.setBounds(375, 75, 90, 36);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"please input orderID");
                    return;
                }
                Order order = new MysqlDao().findByOrderUUID(textField.getText());
                if(order!=null){
                    OrderDetailsView orderDetailsView = new OrderDetailsView(order);
                    orderDetailsView.setVisible(true);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"Did not find this order");
                    return;
                }

            }
        });
        contentPane.add(check);

        JLabel lblYourOrderNumber = new JLabel("Order number:");
        lblYourOrderNumber.setFont(new Font("SimSun", Font.PLAIN, 23));
        lblYourOrderNumber.setBounds(10, 20, 347, 35);
        contentPane.add(lblYourOrderNumber);
    }
}
