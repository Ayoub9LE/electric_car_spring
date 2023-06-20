package ma.ensias.app.entity;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	@Column(name = "user_id")
	    private Integer id;
	    @Column(name="first_name",length=50)
	    private String firstName;
	    @Column(name="last_name",length=50)
	    private String lastName;
	    @Column(name="email",length=100)
	    private String email;
	    @Column(name="password",length=20)
	    private String password;
	    @Column(name="phonenumber",length=20)
	    private String phoneNumber;
	    @Column(name="adress",length=20)
	    private String adress;
	    @Column(name="Created_at",length=20)
	    private Date creation_date;


	    

	    public User(Integer userId, String firstName, String lastName, String email, String password, String phonenumber,
				String adress, Date creation_date) {
			super();
			this.id = userId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			this.phoneNumber = phonenumber;
			this.adress = adress;
			this.creation_date = creation_date;
		}

		public Integer getUserId() {
	        return id;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setUserId(Integer userId) {
	        this.id = userId;
	        
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	        
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	        
	    }

	    public void setPassword(String password) {
	        this.password = password;
	        
	    }

		public String getPhonenumber() {
			return phoneNumber;
			
		}

		public void setPhonenumber(String phonenumber) {
			this.phoneNumber = phonenumber;
			
		}

		public String getAdress() {
			return adress;
			
		}

		public void setAdress(String adress) {
			this.adress = adress;
		}

		public Date getCreation_date() {
			return creation_date;
		
		}

		public void setCreation_date(Date creation_date) {
			this.creation_date = creation_date;
		}
		
	}


