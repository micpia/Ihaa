package pl.piasek.ihaa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Runs {
    private int id;
    private double time;
    private int runNumber;
    private Timestamp timestamp;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = false, precision = 0)
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Basic
    @Column(name = "run_number", nullable = false)
    public int getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(int runNumber) {
        this.runNumber = runNumber;
    }

    @Basic
    @Column(name = "timestamp", nullable = true)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Runs runs = (Runs) o;
        return id == runs.id &&
                Double.compare(runs.time, time) == 0 &&
                runNumber == runs.runNumber &&
                Objects.equals(timestamp, runs.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, runNumber, timestamp);
    }
}
