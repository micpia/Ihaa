package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(RidersViewCompositeKey.class)
@Table(name = "riders_view", schema = "ihaadb", catalog = "")
public class RidersView {
    @Id
    private String name;
    @Id
    private String surname;
    private String country;
    private Byte lefthanded;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "lefthanded", nullable = true)
    public Byte getLefthanded() {
        return lefthanded;
    }

    public void setLefthanded(Byte lefthanded) {
        this.lefthanded = lefthanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RidersView that = (RidersView) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(country, that.country) &&
                Objects.equals(lefthanded, that.lefthanded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, country, lefthanded);
    }
}
