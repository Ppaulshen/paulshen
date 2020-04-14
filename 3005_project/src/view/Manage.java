package view;

import bean.Book;
import dao.MysqlDao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Manage extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private Vector vData;
    private Vector vName;
    private static DefaultTableModel tableModel;

    /**
     * Create the frame.
     */
    public Manage() {
        setBounds(100, 100, 515, 389);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);



        JButton btnNewButton = new JButton("create");
        btnNewButton.setFont(new Font("SimSun", Font.PLAIN, 18));
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                CreateBook createBook = new CreateBook();
                createBook.setVisible(true);

            }
        });
        btnNewButton.setBounds(51, 38, 104, 39);
        contentPane.add(btnNewButton);

        JButton sell = new JButton("sell");
        sell.setFont(new Font("SimSun", Font.PLAIN, 18));
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SellView sellView = new SellView();
                sellView.setVisible(true);
            }
        });
        sell.setBounds(188, 38, 104, 39);
        contentPane.add(sell);

        JButton btnNewButton_1 = new JButton("delete");
        btnNewButton_1.setFont(new Font("SimSun", Font.PLAIN, 18));
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                String bookName = (String) table.getModel().getValueAt(row, 0);
                MysqlDao dao  = new MysqlDao();
                dao.deleteByName(bookName);

                dispose();
                JOptionPane.showMessageDialog(null, "successful delete");
                showdata();

            }
        });
        btnNewButton_1.setBounds(327, 38, 104, 39);
        contentPane.add(btnNewButton_1);
        showdata();
    }

    public void showdata() {

        //reshow vdata vname
        vData = new Vector();
        vName = new Vector();
        vName.add("name");
        vName.add("author");
        vName.add("genre");
        vName.add("publisher");
        vName.add("pages");
        vName.add("price");
        vName.add("ISBN");

        MysqlDao md = new MysqlDao();

        List<Book>[][] bookList = new List[0][];
        try {
            bookList = new List[][]{new List[]{md.findAll()}};
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Iterator<Book> iterator = bookList[0][0].iterator();
        while (iterator.hasNext()) {
            Book next = iterator.next();

            Vector vRow = new Vector();
            vRow.add(next.getName());
            vRow.add(next.getAuthor());
            vRow.add(next.getGenre());
            vRow.add(next.getPublisher());
            vRow.add(next.getPages());
            vRow.add(next.getPrice());
            vRow.add(next.getISBN());
            vData.add(vRow.clone());
        }
        //Reposition components
        DefaultTableModel model = new DefaultTableModel(vData, vName);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setBounds(51, 104, 382, 218);
        model = new DefaultTableModel(vData, vName);
        table.setModel(model);
        contentPane.add(table);
        table.setModel(model);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(51, 104, 382, 218);
        contentPane.add(scroll);
        model = new DefaultTableModel(vData, vName);
        table.setModel(model);
    }
}
