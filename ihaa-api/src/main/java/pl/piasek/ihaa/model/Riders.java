package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Riders {
    private int id;
    private String name;
    private String surname;
    private Byte lefthanded;
    private Countries countriesByCountriesId;
    private Clubs clubsByClubsId;

    public Riders() {}

    public Riders(String name, String surname, Countries countriesByCountriesId) {
        this.name = name;
        this.surname = surname;
        this.countriesByCountriesId = countriesByCountriesId;
    }

    public Riders(Clubs clubsByClubsId) {
        this();
        this.clubsByClubsId = clubsByClubsId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        Riders riders = (Riders) o;
        return id == riders.id &&
                Objects.equals(name, riders.name) &&
                Objects.equals(surname, riders.surname) &&
                Objects.equals(lefthanded, riders.lefthanded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, lefthanded);
    }

    @ManyToOne
    @JoinColumn(name = "countries_id", referencedColumnName = "id")
    public Countries getCountriesByCountriesId() {
        return countriesByCountriesId;
    }

    public void setCountriesByCountriesId(Countries countriesByCountriesId) {
        this.countriesByCountriesId = countriesByCountriesId;
    }

    @ManyToOne
    @JoinColumn(name = "clubs_id", referencedColumnName = "id")
    public Clubs getClubsByClubsId() {
        return clubsByClubsId;
    }

    public void setClubsByClubsId(Clubs clubsByClubsId) {
        this.clubsByClubsId = clubsByClubsId;
    }
}
