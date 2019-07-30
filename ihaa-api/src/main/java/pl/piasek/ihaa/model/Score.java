package pl.piasek.ihaa.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Score {
    private int runsId;
    private Double score;

    @Id
    @Column(name = "runs_id", nullable = false)
    public int getRunsId() {
        return runsId;
    }

    public void setRunsId(int runsId) {
        this.runsId = runsId;
    }

    @Basic
    @Column(name = "score", nullable = true, precision = 0)
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return runsId == score1.runsId &&
                Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runsId, score);
    }
}
