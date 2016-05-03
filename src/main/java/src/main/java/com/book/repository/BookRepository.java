package src.main.java.com.book.repository;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import src.main.java.com.book.model.Book;

@Repository
public interface BookRepository {
	Book findByTitle(String title) throws JAXBException, ParserConfigurationException, SAXException, IOException;

	void deleteBook(Book book) throws JAXBException, ParserConfigurationException, SAXException, IOException;

	void edit(Book book) throws IOException, JAXBException;

	void remove(Book book) throws JAXBException, ParserConfigurationException, SAXException, IOException;

	void report() throws JAXBException;
}
