package bean;

import java.util.List;

public class ShoppingCar {

    private User user;
    private List<Book> bookList;
    private Integer number;
    private Double totalPrice;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "user=" + user +
                ", bookList=" + bookList +
                ", number=" + number +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
