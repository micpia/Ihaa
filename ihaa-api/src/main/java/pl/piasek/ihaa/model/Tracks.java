package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tracks {
    private int id;
    private String name;
    private int timeLimit;
    private Integer firstRun;
    private Integer lastRun;
    private Styles stylesByStylesId;
    private Integer numberOfTargets;

    public Tracks() {}

    public Tracks(String name, int timeLimit, Integer firstRun, Integer lastRun, Styles stylesByStylesId, Integer numberOfTargets) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.firstRun = firstRun;
        this.lastRun = lastRun;
        this.stylesByStylesId = stylesByStylesId;
        this.numberOfTargets = numberOfTargets;
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
    @Column(name = "time_limit", nullable = false)
    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Basic
    @Column(name = "first_run", nullable = true)
    public Integer getFirstRun() {
        return firstRun;
    }

    public void setFirstRun(Integer firstRun) {
        this.firstRun = firstRun;
    }

    @Basic
    @Column(name = "last_run", nullable = true)
    public Integer getLastRun() {
        return lastRun;
    }

    public void setLastRun(Integer lastRun) {
        this.lastRun = lastRun;
    }

    @Basic
    @Column(name = "number_of_targets", nullable = true)
    public Integer getNumberOfTargets() {
        return numberOfTargets;
    }

    public void setNumberOfTargets(Integer numberOfTargets) {
        this.numberOfTargets = numberOfTargets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracks tracks = (Tracks) o;
        return id == tracks.id &&
                timeLimit == tracks.timeLimit &&
                Objects.equals(name, tracks.name) &&
                Objects.equals(firstRun, tracks.firstRun) &&
                Objects.equals(lastRun, tracks.lastRun) &&
                Objects.equals(numberOfTargets, tracks.numberOfTargets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, timeLimit, firstRun, lastRun);
    }

    @ManyToOne
    @JoinColumn(name = "styles_id", referencedColumnName = "id")
    public Styles getStylesByStylesId() { return stylesByStylesId; }

    public void setStylesByStylesId(Styles stylesByStylesId) { this.stylesByStylesId = stylesByStylesId; }
}
