package view;

import bean.Book;
import bean.ShoppingCar;
import bean.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Shopping cart view
 */
public class CarView extends JFrame{


    private JPanel contentPane;
    private JTable table;
    private Vector vData;
    private Vector vName;
    private static DefaultTableModel tableModel;
    private AtomicReference<Double> countPrice;
    public static ShoppingCar car;



    public CarView(ShoppingCar car) {
        setCar(car);
        setBounds(100, 100, 611, 511);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblShoppingcar = new JLabel("ShoppingCar");
        lblShoppingcar.setFont(new Font("SimSun", Font.PLAIN, 40));
        lblShoppingcar.setBounds(43, 32, 246, 98);
        contentPane.add(lblShoppingcar);

        vData = new Vector();
        vName = new Vector();
        vName.add("name");
        vName.add("price");
        vName.add("number");
        vName.add("operating");

        List<Book> bookList = car.getBookList();
        countPrice = new AtomicReference<>(0.0);
        bookList.forEach(i->{
            Vector vRow = new Vector();
            vRow.add(i.getName());
            vRow.add(i.getPrice());
            vRow.add(1);
            vRow.add("delete");
            countPrice.updateAndGet(v -> v + i.getPrice());
            vData.add(vRow.clone());
        });

        JButton btnBuy = new JButton("submit:"+countPrice+"$");
        btnBuy.setFont(new Font("SimSun", Font.PLAIN, 21));
        btnBuy.setBounds(300, 54, 229, 54);
        btnBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(car.getBookList().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No books to buy, please add books");
                }else {
                    dispose();
                    BuyView buyView = new BuyView(car);
                    buyView.setVisible(true);
                }
            }
        });
        contentPane.add(btnBuy);

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//The table is not allowed to be edited
        };

        table.setBounds(64, 152, 465, 282);
        model = new DefaultTableModel(vData, vName);
        table.setModel(model);
        contentPane.add(table);

        contentPane.add(table);
        table.setModel(model);

        /*Use JScrollPane to load JTable, so that the out-of-range columns can be viewed through the scroll bar */
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(64, 152, 465, 282);
        contentPane.add(scroll);

        model = new DefaultTableModel(vData, vName);
        //Double click to add to cart
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    List<Book> bookList1 = car.getBookList();
                    bookList1.remove(row);
                    dispose();
                    JOptionPane.showMessageDialog(null,"successfully deleted");
                    CarView carView = new CarView(car);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        table.setModel(model);
        showCarData();
    }

    public void showCarData(){
        ShoppingCar car = getCar();
        User user = car.getUser();
        List<Book> bookList = car.getBookList();
        System.out.println(user);
        bookList.forEach(i->{
            System.out.println(i);
        });
    }

    public ShoppingCar getCar() {
        return car;
    }

    public void setCar(ShoppingCar car) {
        this.car = car;
    }



}
