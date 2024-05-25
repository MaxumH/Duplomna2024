package mvc.models;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import mvc.services.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Person {
    private int id;
    @Size(min = 0,max = 30, message = "name can not be shorter then 0 characters")
    @NotEmpty(message = "Not Empty")
    private String name;

    @NotEmpty(message = "Not Empty")
    private String position;
    @Min(value=0,message = "Age should be hiegher than 0")
    private int experience;
    @Email
    @NotEmpty(message = "Not Empty")
    private String email;

    @NotEmpty(message = "Not Empty")
    private String department;
    private String password;

    public Person(){};
    public Person(int id, String name, String position, int experience, String email, String department,String password) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.experience = experience;
        this.email = email;
        this.department = department;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    private FileService fileService;

}
