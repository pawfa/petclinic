package hello.entity;


import hello.security.validation.DateValidation;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Pawel on 2017-07-21.
 */

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @Column(name ="owner_first_name")
    @NotNull
    private String ownerFirstName;

    @Column(name ="owner_last_name")
    @NotNull
    private String ownerLastName;

    @NotNull
    private String species;

    @NotNull
    private String sex;

    @Column(name ="admitted")
    @NotNull(message = "Please provide a date.")
    @DateValidation
    private String dataIn;

    @Column(name ="discharged")
    private String dataOut;

    @Column(name ="pet_status")
    private String status;

    @Column(name ="vet")
    private String vet;

    public Pet() {}

    public Pet(String name, String ownerFirstName, String ownerLastName, String species, String sex, String dataIn, String dataOut, String status, String vet) {
        this.name = name;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.species = species;
        this.sex = sex;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.status = status;
        this.vet = vet;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerFirstName='" + ownerFirstName + '\'' +
                ", ownerLastName='" + ownerLastName + '\'' +
                ", species='" + species + '\'' +
                ", sex='" + sex + '\'' +
                ", dataIn='" + dataIn + '\'' +
                ", dataOut='" + dataOut + '\'' +
                ", status='" + status + '\'' +
                ", vet='" + vet + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
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
