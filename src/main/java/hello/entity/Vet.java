package hello.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@Table(name = "vet")
public class Vet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "speciality")
    @NotEmpty
    private String spec;

    @Column(name = "password")
    @NotEmpty
    private String password;

    public Vet() {
    }

    public Vet(String id,String firstName, String lastName, String spec) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.spec = spec;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Vet{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", spec='" + spec + '\'' +
                '}';
    }
}
