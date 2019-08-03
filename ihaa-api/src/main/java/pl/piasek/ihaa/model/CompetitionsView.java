package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "competitions_view", schema = "ihaadb", catalog = "")
public class CompetitionsView {
    private int id;
    private String name;
    private Date startDay;
    private Byte status;
    private String location;
    private long riders;
    private long horses;
    private long styles;

    @Id
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
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    @Basic
    @Column(name = "riders", nullable = false)
    public long getRiders() {
        return riders;
    }

    public void setRiders(long riders) {
        this.riders = riders;
    }

    @Basic
    @Column(name = "horses", nullable = false)
    public long getHorses() {
        return horses;
    }

    public void setHorses(long horses) {
        this.horses = horses;
    }

    @Basic
    @Column(name = "styles", nullable = false)
    public long getStyles() {
        return styles;
    }

    public void setStyles(long styles) {
        this.styles = styles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionsView that = (CompetitionsView) o;
        return id == that.id &&
                riders == that.riders &&
                horses == that.horses &&
                styles == that.styles &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDay, that.startDay) &&
                Objects.equals(status, that.status) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDay, status, location, riders, horses, styles);
    }
}
