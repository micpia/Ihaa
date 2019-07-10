package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Runs {
    private int id;
    private double time;
    private int runNumber;
    private Timestamp timestamp;
    private Starts startsByStartsId;
    private Tracks tracksByTracksId;

    public Runs() {}

    public Runs(double time, int runNumber, Starts startsByStartsId, Tracks tracksByTracksId) {
        this.time = time;
        this.runNumber = runNumber;
        this.startsByStartsId = startsByStartsId;
        this.tracksByTracksId = tracksByTracksId;
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

    @ManyToOne
    @JoinColumn(name = "starts_id", referencedColumnName = "id")
    public Starts getStartsByStartsId() {
        return startsByStartsId;
    }

    public void setStartsByStartsId(Starts startsByStartsId) {
        this.startsByStartsId = startsByStartsId;
    }

    @ManyToOne
    @JoinColumn(name = "tracks_id", referencedColumnName = "id")
    public Tracks getTracksByTracksId() {
        return tracksByTracksId;
    }

    public void setTracksByTracksId(Tracks tracksByTracksId) {
        this.tracksByTracksId = tracksByTracksId;
    }
}
