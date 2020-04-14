package view;

import dao.MysqlDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class SellView extends JFrame{

    private JPanel contentPane;

    public SellView(){
        setBounds(100, 100, 537, 538);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Label lblNewLabel = new Label("Sales details");
        lblNewLabel.setFont(new Font("SimSun", Font.BOLD, 23));
        lblNewLabel.setBounds(10, 10, 316, 43);
        contentPane.add(lblNewLabel);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(35, 76, 449, 395);
        textArea.setFont(new Font("SimSun", Font.BOLD, 19));
        contentPane.add(textArea);
        String allSellBook = getAllSellBook();
        textArea.setText(allSellBook);
    }

    public String getAllSellBook(){
        try {
            String allSellBook = new MysqlDao().findAllSellBook();
            return allSellBook;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
