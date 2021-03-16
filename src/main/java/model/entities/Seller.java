package model.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seller implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Double basySalary;
    private Departament departament;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
    public Seller(){}
    public Seller(Integer id, String name, String email, Date birthDate, Double basySalary, Departament departament) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.basySalary = basySalary;
        this.departament = departament;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBasySalary() {
        return basySalary;
    }

    public void setBasySalary(Double basySalary) {
        this.basySalary = basySalary;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;

        Seller seller = (Seller) o;

        return getId().equals(seller.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Seller [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", basySalary=" + basySalary +
                ", departament=" + departament +
                ']';
    }
}
