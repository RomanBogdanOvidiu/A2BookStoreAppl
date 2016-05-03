package  src.main.java.com.book.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "books")
public class Books {


    @XmlElement(name = "book", type = Book.class)
	private List<Book> books = new ArrayList<Book>();
    
    
    public Books() {}
    
    public Books(List<Book> books) {
        this.books = books;
    }
 
	public List<Book> getBookList() {
		return books;
	}

	public void setBookList(List<Book> bookList) {
		this.books = bookList;
	}
}
