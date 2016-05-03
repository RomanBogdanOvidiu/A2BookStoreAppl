package src.main.java.com.book.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "src.main.java.com.book.model")
public class Book {

	private String title;
	private String author;
	private String genre;
	private int quantity;
	private double price;

	public boolean equals(Book other) {
		if (!(other instanceof Book)) {
			return false;
		}
		Book that = (Book) other;
		// Custom equality check here.
		return this.title.equals(that.title) && this.author.equals(that.author);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", genre=" + genre + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
}
