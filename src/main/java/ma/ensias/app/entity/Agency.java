package ma.ensias.app.entity;

import javax.persistence.*;

@Entity
public class Agency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String city;

    private int numberOfCars;

    private int numberOfRenters;

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public int getNumberOfRenters() {
        return numberOfRenters;
    }

    public void setNumberOfRenters(int numberOfRenters) {
        this.numberOfRenters = numberOfRenters;
    }
}

