package view;

import bean.Book;
import dao.MysqlDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


/**
 * Create Book View
 */
public class CreateBook extends JFrame {


    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;

    public CreateBook(){
        setBounds(100, 100, 450, 618);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField.setBounds(136, 25, 252, 34);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_1.setBounds(136, 92, 252, 34);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_2.setColumns(10);
        textField_2.setBounds(136, 158, 252, 34);
        contentPane.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_3.setColumns(10);
        textField_3.setBounds(136, 223, 252, 34);
        contentPane.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_4.setColumns(10);
        textField_4.setBounds(136, 289, 252, 34);
        contentPane.add(textField_4);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_5.setColumns(10);
        textField_5.setBounds(136, 361, 252, 34);
        contentPane.add(textField_5);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("SimSun", Font.PLAIN, 20));
        textField_6.setColumns(10);
        textField_6.setBounds(136, 428, 252, 34);
        contentPane.add(textField_6);

        JLabel lblBookName = new JLabel("book name");
        lblBookName.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblBookName.setBounds(27, 34, 99, 25);
        contentPane.add(lblBookName);

        JLabel lblNewLabel = new JLabel("author");
        lblNewLabel.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblNewLabel.setBounds(27, 101, 99, 25);
        contentPane.add(lblNewLabel);

        JLabel lblGenre = new JLabel("genre");
        lblGenre.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblGenre.setBounds(27, 167, 99, 25);
        contentPane.add(lblGenre);

        JLabel lblPublisher = new JLabel("publisher");
        lblPublisher.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblPublisher.setBounds(27, 232, 112, 25);
        contentPane.add(lblPublisher);

        JLabel lblNewLabel_1 = new JLabel("pages");
        lblNewLabel_1.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(27, 298, 84, 25);
        contentPane.add(lblNewLabel_1);

        JLabel lblPrice = new JLabel("price");
        lblPrice.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblPrice.setBounds(27, 370, 84, 25);
        contentPane.add(lblPrice);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setFont(new Font("SimSun", Font.PLAIN, 20));
        lblIsbn.setBounds(27, 437, 84, 25);
        contentPane.add(lblIsbn);

        JButton btnAdd = new JButton("add");
        btnAdd.setFont(new Font("SimSun", Font.PLAIN, 20));
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (textField.getText().equals("") || textField_1.getText().equals("") || textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals("") || textField_5.getText().equals("") || textField_6.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Type cannot be empty");
                }
                Book book =new Book();
                book.setName(textField.getText());
                book.setAuthor(textField_1.getText());
                book.setGenre(textField_2.getText());
                book.setPublisher(textField_3.getText());
                book.setPages(Double.parseDouble(textField_4.getText()));
                book.setPrice(Double.parseDouble(textField_5.getText()));
                book.setISBN(textField_6.getText());


                try {
                    MysqlDao mysqlDao = new MysqlDao();
                    boolean b = mysqlDao.bookNameIsExist(book.getName());
                    if(b){
                        dispose();
                        JOptionPane.showMessageDialog(null, "this book was exist");
                        return;
                    }
                    mysqlDao.createOne(book);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                dispose();
                JOptionPane.showMessageDialog(null, "Added successfully");
            }
        });
        btnAdd.setBounds(264, 498, 124, 41);
        contentPane.add(btnAdd);
    }
}
