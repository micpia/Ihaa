package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "competition_styles", schema = "ihaadb", catalog = "")
public class CompetitionStyles {
    private Integer competitionsId;
    private String competition;
    private String style;

    @Id
    @Column(name = "competitions_id", nullable = true)
    public Integer getCompetitionsId() {
        return competitionsId;
    }

    public void setCompetitionsId(Integer competitionsId) {
        this.competitionsId = competitionsId;
    }

    @Basic
    @Column(name = "competition", nullable = true, length = 255)
    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    @Basic
    @Column(name = "style", nullable = true, length = 255)
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionStyles that = (CompetitionStyles) o;
        return Objects.equals(competitionsId, that.competitionsId) &&
                Objects.equals(competition, that.competition) &&
                Objects.equals(style, that.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionsId, competition, style);
    }
}
