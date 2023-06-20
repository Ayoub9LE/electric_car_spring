package ma.ensias.app.service;

import ma.ensias.app.repository.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import ma.ensias.app.entity.*;

import exceptions.EtAuthException;
import jakarta.persistence.EntityNotFoundException;
@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

	@Override
	 public User validateUser(String email, String password) throws EtAuthException {
        if(email != null) email = email.toLowerCase();
		return userRepository.findByEmailAndPassword(email,password);
    }

	@Override
	public User registerUser(String firstName, String lastName, String email, String password,String phone_number,String adress, Date date) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email= email.toLowerCase();
        if(!pattern.matcher(email).matches()) throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if(count>0) throw new EtAuthException("Email already in use");
        Integer userId = userRepository.Create(firstName,lastName, email,password,phone_number,adress,date);
        return userRepository.findById(userId);
    }
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    // Update
    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhonenumber(user.getPhonenumber());
        existingUser.setAdress(user.getAdress());
        existingUser.setCreation_date(user.getCreation_date());

        return userRepository.save(existingUser);
    }

    
    // Delete
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
	

}
