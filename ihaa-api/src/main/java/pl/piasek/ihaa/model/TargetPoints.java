package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "target_points", schema = "ihaadb", catalog = "")
public class TargetPoints {
    private int runsId;
    private Long targetPointsSum;
    private String targets;

    @Id
    @Column(name = "runs_id", nullable = false)
    public int getRunsId() {
        return runsId;
    }

    public void setRunsId(int runsId) {
        this.runsId = runsId;
    }

    @Basic
    @Column(name = "target_points_sum", nullable = true, precision = 0)
    public Long getTargetPointsSum() {
        return targetPointsSum;
    }

    public void setTargetPointsSum(Long targetPointsSum) {
        this.targetPointsSum = targetPointsSum;
    }

    @Basic
    @Column(name = "targets", nullable = true, length = -1)
    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetPoints that = (TargetPoints) o;
        return runsId == that.runsId &&
                Objects.equals(targetPointsSum, that.targetPointsSum) &&
                Objects.equals(targets, that.targets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runsId, targetPointsSum, targets);
    }
}
