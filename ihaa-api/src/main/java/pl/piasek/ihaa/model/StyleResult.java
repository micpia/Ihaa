package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@IdClass(StyleResultCompositeKey.class)
@Table(name = "style_result", schema = "ihaadb", catalog = "")
public class StyleResult {
    @Id
    private int startsId;
    @Id
    private int stylesId;
    private BigInteger allTargets;
    private long allRuns;
    private Double allScore;


    @Column(name = "starts_id", nullable = false)
    public int getStartsId() {
        return startsId;
    }

    public void setStartsId(int startsId) {
        this.startsId = startsId;
    }


    @Column(name = "styles_id", nullable = false)
    public int getStylesId() {
        return stylesId;
    }

    public void setStylesId(int stylesId) {
        this.stylesId = stylesId;
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
    @Column(name = "all_runs", nullable = false)
    public long getAllRuns() {
        return allRuns;
    }

    public void setAllRuns(long allRuns) {
        this.allRuns = allRuns;
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
        StyleResult that = (StyleResult) o;
        return startsId == that.startsId &&
                stylesId == that.stylesId &&
                allRuns == that.allRuns &&
                Objects.equals(allTargets, that.allTargets) &&
                Objects.equals(allScore, that.allScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startsId, stylesId, allTargets, allRuns, allScore);
    }
}
