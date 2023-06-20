package ma.ensias.app.service;


import java.sql.Date;

import org.springframework.stereotype.Service;

import ma.ensias.app.entity.*;
import exceptions.EtAuthException;

@Service
public interface UserService {
    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String LastName, String email, String password,String phonenumner,String adress,Date created_at) throws EtAuthException;

}
