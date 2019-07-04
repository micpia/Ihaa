package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Countries {
    private int id;
    private String name;

    public Countries(){}

    public Countries(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Countries countries = (Countries) o;
        return id == countries.id &&
                Objects.equals(name, countries.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
