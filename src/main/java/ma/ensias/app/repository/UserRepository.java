package ma.ensias.app.repository;
import ma.ensias.app.entity.*;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import exceptions.EtAuthException;

public interface UserRepository extends JpaRepository<User, Long> {
	Integer Create(String firstName, String LastName, String email, String password,String phonenumner,String adress,Date created_at) throws EtAuthException;
    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);



    User findById(Integer userId);
}

