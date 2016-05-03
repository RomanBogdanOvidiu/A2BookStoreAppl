package src.main.java.com.book.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import src.main.java.com.book.model.Book;
import src.main.java.com.book.model.Books;
import src.main.java.com.book.model.User;
import src.main.java.com.book.model.UserRole;
import src.main.java.com.book.model.Users;
import src.main.java.com.book.repository.BookHandler;
import src.main.java.com.book.repository.UserHandler;
import src.main.java.com.book.service.BookService;
import src.main.java.com.book.service.UserService;

@Controller
public class MainController {

	@Autowired
	protected UserService userService;
	@Autowired
	protected BookService bookService;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Book Application");
		model.addObject("message", "Main Page: Welcome to our book store!");
		model.setViewName("welcome");
		return model;

	}

	// ///////////////////////////STUUF ON ADMIN PAGE////////////////////////
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() throws JAXBException, ParserConfigurationException, SAXException, IOException {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Book Application");
		model.addObject("message", "Admin page");

		Users users = userService.usersUnh();

		model.addObject("users", users.getUserList());
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/admin/edituser/{userName}", method = RequestMethod.GET)
	public ModelAndView editUSER(@PathVariable("userName") String username)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		User user = userService.findByUsername(username);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("edituser");
		return model;
	}

	@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST)
	public String editUSER2(User user) throws JAXBException, ParserConfigurationException, SAXException, IOException {
		userService.edit(user);
		return "redirect:/admin";

	}

	@RequestMapping(value = "/admin/delete/{userName}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("userName") String username)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		User user = userService.findByUsername(username);
		userService.deleteUser(user);
		return "redirect:/admin";

	}

	/////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "welcome";
	}

	// for 403 access denied page
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("accessDenied");
		return model;

	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@RequestMapping(value = "/sucCreated", method = RequestMethod.GET)
	public String succesfullOperation() {
		return "/sucCreated";

	}

	@RequestMapping(value = "/wronginput", method = RequestMethod.GET)
	public String wrong() {
		return "/wronginput";

	}

	// SINGUP APPL

	@RequestMapping(value = "/signup", method = RequestMethod.GET)

	public ModelAndView signup() {

		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Book Application");
		forSignUp.addObject("message", "Create new user account");
		User newUser = new User();
		forSignUp.addObject("user", newUser);
		forSignUp.setViewName("signup");
		return forSignUp;

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(User u1) {

		try {
			if (!u1.getNume().equals("") && !u1.getPassword().equals("") && !u1.getPrenume().equals("")
					&& !u1.getUserName().equals("")) {

				List<User> users = new ArrayList<User>();

				if (users.iterator() != null) {
					Set<UserRole> roleSet = new HashSet<UserRole>(0);
					UserRole uR = new UserRole(u1, "ROLE_USER");
					roleSet.add(uR);

					u1.setUserRole(roleSet);

					users = UserHandler.unmarshal(new File("users.xml"));

					users.add(u1);
					UserHandler.marshal(users, new File("users.xml"));

				}
				return "redirect:/sucCreated";
			}
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
			return "redirect:/wronginput";
		}

		return "wronginput";
	}

	// ca sa apara acolo tabelu cu cartile
	@RequestMapping(value = "/bookStore", method = RequestMethod.GET)
	public ModelAndView bookStore() throws JAXBException, ParserConfigurationException, SAXException, IOException {
		ModelAndView bookstore = new ModelAndView();
		bookstore.addObject("title", "BookStore");
		bookstore.addObject("message", "This is your Book Store");
		List<Book> books = new ArrayList<Book>();

		books = BookHandler.unmarshal(new File("books.xml"));
		bookstore.addObject("books", books);
		bookstore.setViewName("bookStore");
		return bookstore;
	}

	// DELETE 1 FROM BOOK QUANTITY
	@RequestMapping(value = "/admin/bookStore/sell/{title}", method = RequestMethod.GET)
	public String sellBook(@PathVariable("title") String title)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		Book book = bookService.findByTitle(title);
		bookService.deleteBook(book);
		return "redirect:/bookStore";

	}

	/// DELETE THE ENTIRE BOOK OBJECT
	@RequestMapping(value = "/admin/bookStore/delete/{title}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("title") String title)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		Book book = bookService.findByTitle(title);
		bookService.remove(book);
		return "redirect:/bookStore";

	}

	//// EDIT ZA BOOK

	@RequestMapping(value = "/bookStore/editBook/{title}", method = RequestMethod.GET)
	public ModelAndView editBook(@PathVariable("title") String title)
			throws JAXBException, ParserConfigurationException, SAXException, IOException {

		Book book = bookService.findByTitle(title);
		ModelAndView model = new ModelAndView();
		model.addObject("books", book);
		model.setViewName("editBook");
		return model;
	}

	@RequestMapping(value = "/bookStore/editBook", method = RequestMethod.POST)
	public String editBook2(Book book) throws JAXBException, ParserConfigurationException, SAXException, IOException {
		bookService.edit(book);
		return "redirect:/bookStore";

	}
	// ADD A NEW BOOK

	@RequestMapping(value = "/bookStore/newBook", method = RequestMethod.GET)

	public ModelAndView addBook() {

		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Book Application");
		forSignUp.addObject("message", "Add a new book to the bookstore");
		Book newBook = new Book();
		forSignUp.addObject("books", newBook);
		forSignUp.setViewName("newBook");
		return forSignUp;

	}

	@RequestMapping(value = "/bookStore/newBook", method = RequestMethod.POST)
	public String addBook(Book b1) throws IOException, JAXBException {

		if (!b1.getTitle().equals("") && !b1.getAuthor().equals("") && !b1.getGenre().equals("") && b1.getQuantity() > 0
				&& b1.getPrice() > 0.0) {

			List<Book> books = new ArrayList<Book>();

			books = BookHandler.unmarshal(new File("books.xml"));
			// Iterator<Book> iterator = books.iterator();

			// while (iterator.hasNext()) {

			// if (iterator.next().equals(b1)) {
			// iterator.next().setQuantity(iterator.next().getQuantity() +
			// b1.getQuantity());
			// } else
			books.add(b1);
			// }
			// for (Book b : books) {
			// if (b.equals(b1)) {
			// b.setQuantity(10);
			// } else
			// books.add(b1);
			// }
			BookHandler.marshal(books, new File("books.xml"));

			return "redirect:/bookStore";
		}

		return "wronginput";
	}

}
