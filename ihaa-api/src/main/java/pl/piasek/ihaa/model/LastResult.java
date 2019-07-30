package pl.piasek.ihaa.model;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@IdClass(LastResultCompositeKey.class)
@Table(name = "last_result", schema = "ihaadb", catalog = "")
public class LastResult {
    private Timestamp timestamp;
    @Id
    private String name;
    @Id
    private String surname;
    private String horse;
    @Id
    private String competition;
    @Id
    private String styles;
    @Id
    private int run;
    private double time;
    private String targets;
    private Double score;
    private Long target;
    private long allRuns;
    private BigInteger allTargets;
    private Double allScore;

    @Basic
    @Column(name = "timestamp", nullable = true)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "surname", nullable = false, length = 255)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "horse", nullable = false, length = 255)
    public String getHorse() {
        return horse;
    }

    public void setHorse(String horse) {
        this.horse = horse;
    }


    @Column(name = "competition", nullable = false, length = 255)
    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }


    @Column(name = "styles", nullable = false, length = 255)
    public String getStyles() {
        return styles;
    }

    public void setStyles(String styles) {
        this.styles = styles;
    }


    @Column(name = "run", nullable = false)
    public int getRun() {
        return run;
    }

    public void setRun(int run) {
        this.run = run;
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
    @Column(name = "targets", nullable = true, length = -1)
    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    @Basic
    @Column(name = "score", nullable = true, precision = 0)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Basic
    @Column(name = "target", nullable = true, precision = 0)
    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
        this.target = target;
    }

    @Basic
    @Column(name = "all_runs", nullable = false)
    public long getAllRuns() {
        return allRuns;
    }

    public void setAllRuns(long allRuns) {
        this.allRuns = allRuns;
    }

    @Basic
    @Column(name = "all_targets", nullable = true, precision = 0)
    public BigInteger getAllTargets() {
        return allTargets;
    }

    public void setAllTargets(BigInteger allTargets) {
        this.allTargets = allTargets;
    }

    @Basic
    @Column(name = "all_score", nullable = true, precision = 0)
    public Double getAllScore() {
        return allScore;
    }

    public void setAllScore(Double allScore) {
        this.allScore = allScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastResult that = (LastResult) o;
        return run == that.run &&
                Double.compare(that.time, time) == 0 &&
                allRuns == that.allRuns &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(horse, that.horse) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(styles, that.styles) &&
                Objects.equals(targets, that.targets) &&
                Objects.equals(score, that.score) &&
                Objects.equals(target, that.target) &&
                Objects.equals(allTargets, that.allTargets) &&
                Objects.equals(allScore, that.allScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, name, surname, horse, competition, styles, run, time, targets, score, target, allRuns, allTargets, allScore);
    }
}
