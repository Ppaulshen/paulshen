package bean;



public class Book {
    private String name;
    private String author;
    private String genre;//
    private String publisher;
    private Double pages;
    private Double price;
    private String ISBN;

    public Book() {
    }

    public Book(String name, String author, String genre, String publisher, Double pages, Double price, String ISBN) {

        this.name = name;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
        this.price = price;
        this.ISBN = ISBN;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPages() {
        return pages;
    }

    public void setPages(Double pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
