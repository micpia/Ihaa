package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Competitions {
    private int id;
    private String name;
    private Date startDay;
    private Boolean status;
    private String location;

    public Competitions() {}

    public Competitions(String name) {
        this.name = name;
    }

    public Competitions(String name, Date startDay, Boolean status, String location) {
        this.name = name;
        this. startDay = startDay;
        this.status = status;
        this.location = location;
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
    @Column(name = "start_day", nullable = true)
    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competitions that = (Competitions) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDay, that.startDay) &&
                Objects.equals(status, that.status) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDay, status, location);
    }
}
