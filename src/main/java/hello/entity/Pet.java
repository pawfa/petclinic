package hello.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pawel on 2017-07-21.
 */

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String owner;
    private String species;
    private String sex;

    @Column(name ="admitted")
    private String dataIn;

    @Column(name ="discharged")
    private String dataOut;

    @Column(name ="pet_status")
    private String status;

    @Column(name ="vet")
    private String vet;

    protected Pet() {}

    public Pet(String name, String owner, String species, String sex, String dataIn, String dataOut) {
        this.name = name;
        this.owner = owner;
        this.species = species;
        this.sex = sex;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", species='" + species + '\'' +
                ", sex='" + sex + '\'' +
                ", dataIn='" + dataIn + '\'' +
                ", dataOut='" + dataOut + '\'' +
                ", dataOut='" + dataOut + '\'' +
                ", vet='" + vet + '\'' +
                '}';
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDataIn() {
        return dataIn;
    }

    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }

    public String getDataOut() {
        return dataOut;
    }

    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
