package src.main.java.com.book.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import src.main.java.com.book.model.Book;
import src.main.java.com.book.model.Books;
import src.main.java.com.book.model.User;
import src.main.java.com.book.repository.BookHandler;
import src.main.java.com.book.repository.BookRepository;
import src.main.java.com.book.repository.UserHandler;

@Service
public class BookService implements BookRepository {
	@Override
	public Book findByTitle(String title)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		JAXBContext context = JAXBContext.newInstance(Books.class);
		Unmarshaller um = context.createUnmarshaller();
		UnmarshallerHandler handler = um.getUnmarshallerHandler();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(handler);
		xr.parse("books.xml");
		Books books = (Books) handler.getResult();

		System.out.println("*******************************JAXB exception");
		for (Book b : books.getBookList()) {

			System.out.println("******************************Ion o intrat in formatie");
			if (b.getTitle().equals(title))
				return b;

		}

		return null;

	}

	@Override
	public void deleteBook(Book book) throws JAXBException, ParserConfigurationException, SAXException, IOException {

		System.out.println("*******************************ESTI IN DELETEE");

		List<Book> books = new ArrayList<Book>();

		books = BookHandler.unmarshal(new File("books.xml"));
		
		for(Book b : books)
		{
			if(b.equals(book) ){
				if(b.getQuantity() >0) b.setQuantity(b.getQuantity()-1);
				else books.remove(b);
			}
			
		}
		BookHandler.marshal(books, new File("books.xml"));

	}
	
	@Override
	public void remove(Book book) throws JAXBException, ParserConfigurationException, SAXException, IOException {

		System.out.println("*******************************ESTI IN DELETEE");

		List<Book> books = new ArrayList<Book>();

		books = BookHandler.unmarshal(new File("books.xml"));
		Iterator<Book> iterator = books.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().equals(book)) {

				iterator.remove();

			}
		}
	
		BookHandler.marshal(books, new File("books.xml"));

	}




	@Override
	public void edit(Book book) throws IOException, JAXBException {
		List<Book> books = new ArrayList<Book>();

		books = BookHandler.unmarshal(new File("books.xml"));
		for (Book u : books) {
			if (u.getTitle().equals(book.getTitle())) {
				u.setTitle(book.getTitle());
				u.setAuthor(book.getAuthor());
				u.setGenre(book.getGenre());
				u.setQuantity(book.getQuantity());
				u.setPrice(book.getPrice());
			}
		}
		BookHandler.marshal(books, new File("books.xml"));
	}

	@Override
	public void report() throws JAXBException {
		
		int sum = 0;
		List<Book> books = new ArrayList<Book>();

		List<Book> boky = new ArrayList<Book>();

		books = BookHandler.unmarshal(new File("books.xml"));

		Iterator<Book> iterator = books.iterator();

		while (iterator.hasNext()) {
			//
			if (iterator.next().getQuantity() == 0) {
				sum++;
				boky.add(sum, iterator.next());
			}
			
		}

	}
}


//public Books bookUnh() throws JAXBException, ParserConfigurationException, SAXException, IOException {
//
//	JAXBContext context = JAXBContext.newInstance(Books.class);
//	Unmarshaller um = context.createUnmarshaller();
//	UnmarshallerHandler handler = um.getUnmarshallerHandler();
//
//	SAXParserFactory spf = SAXParserFactory.newInstance();
//	SAXParser sp = spf.newSAXParser();
//	XMLReader xr = sp.getXMLReader();
//	xr.setContentHandler(handler);
//	xr.parse("books.xml");
//	System.out.println("MERE CALU PRIN PADURE: TAGADAM TAGADAM TAGADAM");
//	Books books = (Books) handler.getResult();
//
//	return books;
//
//}
