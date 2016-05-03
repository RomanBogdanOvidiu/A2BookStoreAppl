package  src.main.java.com.book.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import  src.main.java.com.book.model.Book;
import  src.main.java.com.book.model.Books;
 

public class BookHandler {
	  // Export
    public static void marshal(List<Book> books, File selectedFile)
            throws IOException, JAXBException {
        JAXBContext context;
        BufferedWriter writer = null;
        writer = new BufferedWriter(new FileWriter(selectedFile));
        context = JAXBContext.newInstance(Books.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new Books(books), writer);
        writer.close();
    }
 
    // Import
    public static List<Book> unmarshal(File importFile) throws JAXBException {
        Books books = new Books();
 
        JAXBContext context = JAXBContext.newInstance(Books.class);
        Unmarshaller um = context.createUnmarshaller();
        books = (Books) um.unmarshal(importFile);
 
        return books.getBookList();
    }
}
