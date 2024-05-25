package mvc.services;

import mvc.dao.PersonDAO;
import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PersonDAO personDAO;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerUser(String username, String rawPassword) {
        String hashedPassword = passwordEncoder.encode(rawPassword);
        Person person = new Person();
       person.setName(username);
        person.setPassword(rawPassword);
       personDAO.save(person);
    }

    public boolean checkPassword(String rawPassword, String storedHashedPassword) {
        return passwordEncoder.matches(rawPassword, storedHashedPassword);
    }
}
