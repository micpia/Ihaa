package pl.piasek.ihaa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Styles {
    private int id;
    private String name;
    private double pointsPerSecond;
    private int numberOfRuns;

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
    @Column(name = "points_per_second", nullable = false, precision = 0)
    public double getPointsPerSecond() {
        return pointsPerSecond;
    }

    public void setPointsPerSecond(double pointsPerSecond) {
        this.pointsPerSecond = pointsPerSecond;
    }

    @Basic
    @Column(name = "number_of_runs", nullable = false)
    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public void setNumberOfRuns(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Styles styles = (Styles) o;
        return id == styles.id &&
                Double.compare(styles.pointsPerSecond, pointsPerSecond) == 0 &&
                numberOfRuns == styles.numberOfRuns &&
                Objects.equals(name, styles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pointsPerSecond, numberOfRuns);
    }
}
