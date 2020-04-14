package dao;

import bean.Book;
import bean.Order;
import util.MysqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Used to encapsulate some atomic operations
 */
public class MysqlDao {

    //create
    public void createOne(Book book) throws SQLException {
        Connection conn = MysqlUtil.getConnection();
        //sql
        String sql = "INSERT INTO book(name, author, genre, publisher, pages, price,ISBN) values(?,?,?,?,?,?,?)";

        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, book.getName());
        ptmt.setString(2, book.getAuthor());
        ptmt.setString(3, book.getGenre());
        ptmt.setString(4, book.getPublisher());
        ptmt.setDouble(5, book.getPages());
        ptmt.setDouble(6, book.getPrice());
        ptmt.setString(7, book.getISBN());
        //execute
        ptmt.execute();


    }

    //find
    public List<Book> findAll() throws SQLException {
        Connection connection = MysqlUtil.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name,author,genre,publisher,pages,price,ISBN FROM book");
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
        return bookList;
    }

    public void deleteByName(String name) {

        Connection conn = MysqlUtil.getConnection();
        String sql = "delete from book where name=?";

        PreparedStatement ptmt = null;
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean bookNameIsExist(String name){
        Connection conn = MysqlUtil.getConnection();
        String sql = "select * from book where name=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while (resultSet.next()){
                i++;
            }
            if(i>=1){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void orderInsert(Order order) throws SQLException {
        Connection conn = MysqlUtil.getConnection();
        //sql
        String sql = "INSERT INTO userOrder(orderUUID, userRealName, userRealPhoneNumber, userRealAddress, userName, orderStatus, orderBooks) values(?,?,?,?,?,?,?)";

        PreparedStatement ptmt = conn.prepareStatement(sql);

        ptmt.setString(1, order.getOrderUUID());
        ptmt.setString(2, order.getUserRealName());
        ptmt.setString(3, order.getUserRealPhoneNumber());
        ptmt.setString(4, order.getUserRealAddress());
        ptmt.setString(5, order.getUserName());
        ptmt.setString(6, order.getOrderStatus());
        ptmt.setString(7, order.getOrderBooks());
        //execute
        ptmt.execute();
    }

    public Order findByOrderUUID(String UUID){
        Connection conn = MysqlUtil.getConnection();
        String sql = "select * from userorder where orderUUID=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,UUID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Order order = new Order();
                order.setOrderUUID(resultSet.getString("orderUUID"));
                order.setUserRealName(resultSet.getString("userRealName"));
                order.setUserRealPhoneNumber(resultSet.getString("userRealPhoneNumber"));
                order.setUserRealAddress(resultSet.getString("userRealAddress"));
                order.setUserName(resultSet.getString("userName"));
                order.setOrderStatus(resultSet.getString("orderStatus"));
                order.setOrderBooks(resultSet.getString("orderBooks"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static String findAllSellBook() throws SQLException {
        Connection connection = MysqlUtil.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT username,orderBooks FROM userorder");
        StringBuilder stb = new StringBuilder();
        while (rs.next()) {
            stb.append(rs.getString("username")+" bought :");
            stb.append(rs.getString("orderBooks")+"#");
        }
        if(stb==null){
            return "";
        }
        String s = stb.toString();
        String[] split = s.split("#");
        String sellBook ="";
        for (int i = 0; i <split.length ; i++) {
            System.out.println(split[i]);
           sellBook+=split[i].substring(0,split[i].length()-1)+"\n";
        }
        return sellBook;

//        System.out.println(stb.toString());

    }

}
