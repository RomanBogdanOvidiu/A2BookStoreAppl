//package  src.main.java.com.book.main;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.xml.bind.JAXBException;
//
//import  src.main.java.com.book.model.Book;
//import  src.main.java.com.book.model.User;
//import  src.main.java.com.book.model.UserRole;
//import  src.main.java.com.book.repository.BookHandler;
//import  src.main.java.com.book.repository.UserHandler;
//
//public class MainTesting {
//   public static void main(String[] args) {
//	   User user=new User();
//	   Set<UserRole> roleSet = new HashSet<UserRole>(0);
//		UserRole uR = new UserRole(user, "ROLE_ADMIN");
//		roleSet.add(uR);
//       
//       user.setNume("Admin");
//       user.setPrenume("Boss");
//       user.setUserName("root");
//       user.setPassword("1010");
//       user.setUserRole(roleSet);
//       
//       User user2=new User();
//       user2.setNume("Laura");
//       user2.setPrenume("Dan");
//       user2.setUserName("Laury");
//       user2.setPassword("9999");
//       user2.setUserRole(roleSet);
//       
//       
//       
//       
//       List<User> users=new ArrayList<User>();
//       users.add(user);
//       users.add(user2);
//       //Marshalling: Writing Java objects to XMl file
//       try {
//           UserHandler.marshal(users, new File("users.xml"));
//       } catch (IOException e) {
//           e.printStackTrace();
//       } catch (JAXBException e) {
//           e.printStackTrace();
//       }
//        
//       //Unmarshalling: Converting XML content to Java objects
//     try {
//          users = UserHandler.unmarshal(new File("users.xml"));
//     } catch (JAXBException e) {
//        e.printStackTrace();
//   }
//       System.out.println(users);
//   }
//}
