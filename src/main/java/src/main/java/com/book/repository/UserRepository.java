package src.main.java.com.book.repository;

import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import src.main.java.com.book.model.User;
import src.main.java.com.book.model.Users;

@Repository
public interface UserRepository {

	User findByUsername(String username) throws JAXBException, ParserConfigurationException, SAXException, IOException;

	Users usersUnh() throws JAXBException, ParserConfigurationException, SAXException, IOException;

	void deleteUser(User user) throws JAXBException, ParserConfigurationException, SAXException, IOException;

	
}
