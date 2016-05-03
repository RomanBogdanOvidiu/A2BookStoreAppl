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

import src.main.java.com.book.repository.UserHandler;
import src.main.java.com.book.model.User;
import src.main.java.com.book.model.Users;
import src.main.java.com.book.repository.UserRepository;

@Service
public class UserService implements UserRepository {

	@Override
	public User findByUsername(String username)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		JAXBContext context = JAXBContext.newInstance(Users.class);
		Unmarshaller um = context.createUnmarshaller();
		UnmarshallerHandler handler = um.getUnmarshallerHandler();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(handler);
		xr.parse("users.xml");
		Users users = (Users) handler.getResult();

		System.out.println("*O intrat in loop de cautare a userului******************************");
		for (User u : users.getUserList()) {

			if (u.getUserName().equals(username))
				return u;

		}

		return null;

	}

	public void edit(User user) throws JAXBException, IOException {
		List<User> users = new ArrayList<User>();

		users = UserHandler.unmarshal(new File("users.xml"));
		for (User u : users) {
			if (u.getUserName().equals(user.getUserName())) {
				u.setNume(user.getNume());
				u.setPrenume(user.getPrenume());
				u.setUserName(user.getUserName());
			}
		}
		UserHandler.marshal(users, new File("users.xml"));
	}

	@Override
	public void deleteUser(User user) throws JAXBException, ParserConfigurationException, SAXException, IOException {

		System.out.println("*******************************ESTI IN DELETEE");

		List<User> users = new ArrayList<User>();

		users = UserHandler.unmarshal(new File("users.xml"));
		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {
			if (iterator.next().equals(user)) {

				iterator.remove();

			}
		}
		UserHandler.marshal(users, new File("users.xml"));

	}

	@Override
	public Users usersUnh() throws JAXBException, ParserConfigurationException, SAXException, IOException {

		JAXBContext context = JAXBContext.newInstance(Users.class);
		Unmarshaller um = context.createUnmarshaller();
		UnmarshallerHandler handler = um.getUnmarshallerHandler();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		xr.setContentHandler(handler);
		xr.parse("users.xml");
		System.out.println("MERE CALU PRIN PADURE: TAGADAM TAGADAM TAGADAM");
		Users users = (Users) handler.getResult();

		return users;

	}

}
