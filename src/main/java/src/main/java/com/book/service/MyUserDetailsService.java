package src.main.java.com.book.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import src.main.java.com.book.model.UserRole;
import src.main.java.com.book.repository.UserRepository;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	// @Autowired
	// private UserDao userDao;
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		// com.users.model.User user = userDao.findByUserName(username);
		src.main.java.com.book.model.User user = null;
		try {
			user = userRepository.findByUsername(username);
		} catch (JAXBException | ParserConfigurationException | SAXException | IOException | NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);

	}

	// Converts com.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(src.main.java.com.book.model.User user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), user.getPassword(), authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}