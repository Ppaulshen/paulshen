package bean;

/**
 * Database order model structure
 */
public class Order {
    private String orderUUID;
    private String userRealName;
    private String userRealPhoneNumber;
    private String userRealAddress;
    private String userName;
    private String orderStatus;
    private String orderBooks;

    @Override
    public String toString() {
        return "order{" +
                "orderUUID='" + orderUUID + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userRealPhoneNumber='" + userRealPhoneNumber + '\'' +
                ", userRealAddress='" + userRealAddress + '\'' +
                ", userName='" + userName + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderBooks='" + orderBooks + '\'' +
                '}';
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserRealPhoneNumber() {
        return userRealPhoneNumber;
    }

    public void setUserRealPhoneNumber(String userRealPhoneNumber) {
        this.userRealPhoneNumber = userRealPhoneNumber;
    }

    public String getUserRealAddress() {
        return userRealAddress;
    }

    public void setUserRealAddress(String userRealAddress) {
        this.userRealAddress = userRealAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderBooks() {
        return orderBooks;
    }

    public void setOrderBooks(String orderBooks) {
        this.orderBooks = orderBooks;
    }
}
