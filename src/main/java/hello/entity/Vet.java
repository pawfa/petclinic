package hello.entity;

import javax.persistence.*;

@Entity
@Table(name = "vet")
public class Vet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "speciality")
    private String spec;

    protected Vet() {
    }

    public Vet(String firstName, String lastName, String spec) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.spec = spec;
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
