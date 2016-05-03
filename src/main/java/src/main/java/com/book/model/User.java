package src.main.java.com.book.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "src.main.java.com.book.model")
public class User {

	private String nume;
	private String prenume;
	private String userName;
	private String password;

	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	public boolean equals(User other) {
		if (!(other instanceof User)) {
			return false;
		}

		User that = (User) other;

		// Custom equality check here.
		return this.userName.equals(that.userName);
	}

	public User(String nume, String prenume, String username, String password) {
		this.userName = username;
		this.password = password;
		this.nume = nume;
		this.prenume = prenume;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [nume=" + nume + ", prenume=" + prenume + ", userName=" + userName + ", password=" + password
				+ ", getUserRole=" + getUserRole() + "]";
	}
}
