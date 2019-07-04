package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Shots {
    private int id;
    private int points;
    private Integer shotNumber;
    private Runs runsByRunsId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "points", nullable = false)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Basic
    @Column(name = "shot_number", nullable = true)
    public Integer getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(Integer shotNumber) {
        this.shotNumber = shotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shots shots = (Shots) o;
        return id == shots.id &&
                points == shots.points &&
                Objects.equals(shotNumber, shots.shotNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, shotNumber);
    }

    @ManyToOne
    @JoinColumn(name = "runs_id", referencedColumnName = "id", nullable = false)
    public Runs getRunsByRunsId() {
        return runsByRunsId;
    }

    public void setRunsByRunsId(Runs runsByRunsId) {
        this.runsByRunsId = runsByRunsId;
    }
}
