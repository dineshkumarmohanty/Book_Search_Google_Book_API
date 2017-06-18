package singlepageapp.mohanty.dinesh.com.booksearch;

public class Book {


    private String bookNmae;
    private String bookTitle;
    private String Link;



    public Book(String bookNmae , String bookTitle , String Link)
    {
        this.bookNmae = bookNmae;
        this.bookTitle = bookTitle;
        this.Link = Link;


    }


    public String getBookName()
    {
        return bookNmae;
    }
    public String getBookTitle()
    {
        return bookTitle;
    }


    public String getLink()
    {
        return Link;
    }


}
