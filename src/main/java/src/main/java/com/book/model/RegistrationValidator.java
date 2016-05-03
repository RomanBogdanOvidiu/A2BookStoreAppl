package  src.main.java.com.book.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import  src.main.java.com.book.model.User;

@Component
public class RegistrationValidator implements Validator {

        public boolean supports(Class<?> c) {
                return User.class.isAssignableFrom(c);
        }

        public void validate(Object command, Errors errors) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nume", "field.nume.empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenume", "field.prenume.empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.userName.empty");
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.password.empty");
               // User usrBean = (User)command;
             //   if(!isNumber(usrBean.getAge().trim()))
                 //       errors.rejectValue("age", "field.age.NAN");
        }

        private boolean isNumber(String str){
            for (int i = 0; i < str.length(); i++) {

                //If we find a non-digit character we return false.
                if (!Character.isDigit(str.charAt(i)))
                return false;
                }

                return true;
        }
}