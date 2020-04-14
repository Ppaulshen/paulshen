package view;


import bean.Book;
import bean.ShoppingCar;
import bean.User;
import dao.MysqlDao;
import util.MysqlUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Main window
 */
public class WindowDesign extends JFrame {

    public static JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private JComboBox comboBox;
    private Vector vData;
    private Vector vName;
    private static DefaultTableModel tableModel;
    private User user;
    public static List<Book> carBooks;
    private JButton login;
    private Label loginUser;

    private String loginStatus = "login";

    public void frameIsVisible(boolean Test) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WindowDesign frame = new WindowDesign();
                    frame.setVisible(Test);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public WindowDesign() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 755, 613);
        setTitle("online bookstore");
        contentPane = new JPanel();
        contentPane.setFont(new Font("SimSun", Font.PLAIN, 18));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        carBooks = new ArrayList<>();

        loginUser=new Label();
        loginUser.setBounds(500,0,190,40);
        loginUser.setFont(new Font("SimSun", Font.PLAIN, 22));
        contentPane.add(loginUser);

        contentPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (textField.getText().equals("")) {
                    refresh();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (textField.getText().equals("")) {
                    refresh();
                }
            }
        });

        textField = new JTextField();
        textField.setFont(new Font("SimSun", Font.PLAIN, 22));
        textField.setBounds(52, 54, 292, 40);
        textField.setColumns(10);
        contentPane.add(textField);


        JButton button = new JButton("OK");
        button.setFont(new Font("SimSun", Font.PLAIN, 20));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().equals("")) {
                    findAll();
                } else {
                    try {
                        find(textField.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        button.setBounds(367, 54, 111, 40);
        contentPane.add(button);

        comboBox = new JComboBox();
        comboBox.setFont(new Font("SimSun", Font.PLAIN, 22));
        comboBox.setBounds(519, 58, 124, 30);
        comboBox.addItem("author");
        comboBox.addItem("bookName");
        comboBox.addItem("ISBN");
        contentPane.add(comboBox);


        Label title = new Label("online bookstore");
        title.setFont(new Font("SimSun", Font.PLAIN, 22));
        title.setBounds(0,0,211,40);
        contentPane.add(title);

        JButton fresh = new JButton("fresh");
        fresh.setFont(new Font("SimSun", Font.PLAIN, 20));
        fresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        fresh.setBounds(0, 1, 111, 40);
        //remove fresh button
//        contentPane.add(fresh);

        JButton regist = new JButton("regist");
        regist.setFont(new Font("SimSun", Font.PLAIN, 20));
        regist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register();
                register.setVisible(true);
            }
        });
        regist.setBounds(52, 100, 111, 40);
        contentPane.add(regist);

        JButton manage = new JButton("manage");
        manage.setFont(new Font("SimSun", Font.PLAIN, 20));
        manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Login is currently displayed without user login
                if (Login.getLoginUser() == null) {
                    JOptionPane.showMessageDialog(null, "login first");
                } else {
                    //If it is admin, you can access the manage module
                    if (Login.getLoginUser().getName().equals("admin")) {
                        Manage manage1 = new Manage();
                        manage1.setVisible(true);
                        if (manage1.isFocusableWindow()) {
                            refresh();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "error:Please use account 'admin' access");
                    }
                }
            }
        });
        manage.setBounds(290, 100, 111, 40);
        contentPane.add(manage);


        login = new JButton(loginStatus);
        login.setFont(new Font("SimSun", Font.PLAIN, 20));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Determine the login status of the user to display the text of the login button
                if (loginStatus.equals("login")) {
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    Login.setLoginUserNull();
                    loginStatus = "login";
                    JOptionPane.showMessageDialog(null, "Logout successful");
                }

            }
        });
        login.setBounds(172, 100, 111, 40);
        contentPane.add(login);


        JButton checkOrder = new JButton("check");
        checkOrder.setFont(new Font("SimSun", Font.PLAIN, 20));
        checkOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckView checkView = new CheckView();
                checkView.setVisible(true);
            }
        });
        checkOrder.setBounds(410, 100, 111, 40);
        contentPane.add(checkOrder);


        JButton shoppingCar = new JButton("shoppingCar");
        shoppingCar.setFont(new Font("SimSun", Font.PLAIN, 20));
        shoppingCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Build a shopping cart DTO
                refresh();
                User loginUser = Login.getLoginUser();
                if (loginUser == null) {
                    JOptionPane.showMessageDialog(null, "Login please");
                } else {
                    ShoppingCar car = new ShoppingCar();
                    car.setUser(loginUser);
                    car.setBookList(carBooks);
                    CarView carView = new CarView(car);
                    carView.setVisible(true);
                }
            }
        });
        shoppingCar.setBounds(530, 100, 151, 40);
        contentPane.add(shoppingCar);


        //add data
        vData = new Vector();
        vName = new Vector();
        vName.add("name");
        vName.add("author");
        vName.add("genre");
        vName.add("publisher");
        vName.add("pages");
        vName.add("price");
        vName.add("ISBN");
        vData.clear();

        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table = new JTable(tableModel) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setFont(new Font("SimSun", Font.PLAIN, 20));

        table.setRowHeight(29);
        table.setBounds(55, 156, 633, 389);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    if (Login.getLoginUser() == null) {
                        JOptionPane.showMessageDialog(null, "Login first, please");
                    } else {
                        int row = table.getSelectedRow();
                        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                        String value = (String) dtm.getValueAt(row, 0);
                        Book book = new Book();
                        book.setName((String) dtm.getValueAt(row, 0));
                        book.setAuthor((String) dtm.getValueAt(row, 1));
                        book.setGenre((String) dtm.getValueAt(row, 2));
                        book.setPublisher((String) dtm.getValueAt(row, 3));
                        book.setPages((Double) dtm.getValueAt(row, 4));
                        book.setPrice((Double) dtm.getValueAt(row, 5));
                        book.setISBN((String) dtm.getValueAt(row, 6));
                        carBooks.add(book);
                        JOptionPane.showMessageDialog(null, "<" + value + ">Add to Cart successful", "MSG", JOptionPane.INFORMATION_MESSAGE);
                    }
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
        contentPane.add(table);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(55, 156, 633, 389);
        model = new DefaultTableModel(vData, vName);
        table.setModel(model);
        contentPane.add(scroll);
        findAll();
    }

    public void find(String str) throws SQLException {
        //connect database
        Connection connection = MysqlUtil.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT name,author,genre,publisher,pages,price,ISBN FROM book";
        String extra = null;

        //find by author
        if (comboBox.getSelectedItem().toString().trim().equals("author")) {
            extra = "author";
        }
        //find by book name
        if (comboBox.getSelectedItem().toString().trim().equals("bookName")) {
            extra = "name";
        }
        //isbn
        if (comboBox.getSelectedItem().toString().trim().equals("ISBN")) {
            extra = "ISBN";
        }

        System.out.println(sql + " where " + extra + "='" + str + "'");
        ResultSet rs = statement.executeQuery(sql + " where " + extra + "='" + str + "'");
        List<Book> bookList = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setName(rs.getString("name"));
            book.setAuthor(rs.getString("author"));
            book.setGenre(rs.getString("genre"));
            book.setPublisher(rs.getString("publisher"));
            book.setPages(rs.getDouble("pages"));
            book.setPrice(rs.getDouble("price"));
            book.setISBN(rs.getString("ISBN"));
            bookList.add(book);
        }
        System.out.println(bookList);
        if(bookList.isEmpty()){
            JOptionPane.showMessageDialog(null, "No related books found");
        }
        if (bookList != null) {
            vData.clear();
            Iterator<Book> iterator = bookList.iterator();
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
            DefaultTableModel model = new DefaultTableModel(vData, vName);
            table.setModel(model);
        }


    }

    public void findAll() {
        //Find all table data
        vData.clear();
        //get data form database
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
        DefaultTableModel model = new DefaultTableModel(vData, vName);
        table.setModel(model);

    }

    public void refresh() {
        //Restore all components to the default state, and go to the database to query table data
        textField.setText("");
        if (Login.getLoginUser() != null) {
            loginStatus = "logout";
            loginUser.setText("Welcome:"+Login.getLoginUser().getName());
        }
        if(Login.getLoginUser()==null){
            loginUser.setText("");
        }

        login.setText(loginStatus);
        this.findAll();
    }


}
