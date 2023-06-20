package ma.ensias.app.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import ma.ensias.app.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import exceptions.EtAuthException;



public abstract class UserRepositoryImpl implements UserRepository {
	private static final String SQL_CREATE="INSERT INTO USERS(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,PHONENUMBER,ADRESS,CREATED_AT) VALUES(NEXTVAL('ET_USERS_SEQ'), ?, ?, ?, ?,?,?,?)";
    private static final String SQL_COUNT_BY_EMAIL= " SELECT COUNT(*) "+"FROM USERS WHERE EMAIL = ? ";
    private static final String SQL_COUNT_BY_ID= "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD " + " FROM USERS WHERE USER_ID = ?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD" + " FROM USERS WHERE EMAIL = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Integer Create(String firstName, String lastName, String email, String password,String phonenumber,String adress,Date created_at) throws EtAuthException {
        KeyHolder keyHolder;
        try {
            keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setString(5, phonenumber);
                ps.setString(6, adress);
                ps.setDate(7, new java.sql.Date(created_at.getTime()));
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
            throw new EtAuthException("Invalid details. Failed to create account");
        }
    }

    @SuppressWarnings("deprecation")
	@Override
    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email},userRowMapper);
            if(!password.equals(user.getPassword()))
                throw new EtAuthException("Invalid email/password");
            return user;
        }
        catch(EmptyResultDataAccessException e){
            throw new EtAuthException("Invalid email/password");
        }

    }

    @SuppressWarnings("deprecation")
	@Override
    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL,new Object[]{email},Integer.class);
    }

    @SuppressWarnings("deprecation")
	@Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_ID,new Object[]{userId},userRowMapper);

    }
    private RowMapper<User> userRowMapper = ((rs,rowNum) -> {
        return new User(rs.getInt("USER_ID"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("PHONE_NUMBER"),
                rs.getString("ADRESS"),
                rs.getDate("CREATED_AT"));

    });

}
