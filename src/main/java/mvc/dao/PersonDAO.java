package mvc.dao;

import mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Якщо в нас назви сетерів і гетерів співпадають з назвами колонок в БД ми можемо використовувати BeanPropertyRowMapper<> якщо ні треба самостійно написати RowMapper
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

        public void save (Person person) {
            jdbcTemplate.update("INSERT INTO Person VALUES(?,?,?,?,?,?,?)", person.getId(),person.getName(),person.getPosition(),person.getExperience(),person.getEmail(),person.getDepartment(),person.getPassword());
      }
        public Person show ( int id){
            // Тут треба добавити new Object[]{id} це масив через який ми передаємо параметри ? в нашому випадку тільки id
            return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                    .stream().findAny().orElse(null);
        }
        public List<Person> output(String department)
        {
            return jdbcTemplate.query("SELECT * FROM Person WHERE department=?",new Object[]{department},new BeanPropertyRowMapper<>(Person.class));
        }
        public List<Person> search(String name)
        {
            return jdbcTemplate.query("SELECT * FROM Person WHERE name=?",new Object[]{name},new BeanPropertyRowMapper<>(Person.class));

        }
        public Person success(String email,String password)
        {
            return  jdbcTemplate.query("SELECT * FROM Person WHERE email=? AND password=?",new Object[]{email,password},new BeanPropertyRowMapper<>(Person.class))
                    .stream().findAny().orElse(null);
        }

        // тут нам не треба використовувати масив щоб передати параметри тому що вони так придумали метод (alishev так і сказав)
        public void update ( int id, Person updateperson) {
            jdbcTemplate.update("UPDATE Person SET name=?, position=?,experience=?,email=?,department=? WHERE id=?",
                    updateperson.getName(), updateperson.getPosition(), updateperson.getExperience(), updateperson.getEmail(), updateperson.getDepartment(), id);
        }


        public void delete ( int id)
            {
                jdbcTemplate.update("DELETE FROM Person where id=?",id);
            }




}


