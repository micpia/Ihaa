package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Starts {
    private int id;
    private Riders ridersByRidersId;
    private Competitions competitionsByCompetitionsId;
    private Horses horsesByHorsesId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Starts starts = (Starts) o;
        return id == starts.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "riders_id", referencedColumnName = "id", nullable = false)
    public Riders getRidersByRidersId() {
        return ridersByRidersId;
    }

    public void setRidersByRidersId(Riders ridersByRidersId) {
        this.ridersByRidersId = ridersByRidersId;
    }

    @ManyToOne
    @JoinColumn(name = "competitions_id", referencedColumnName = "id", nullable = false)
    public Competitions getCompetitionsByCompetitionsId() {
        return competitionsByCompetitionsId;
    }

    public void setCompetitionsByCompetitionsId(Competitions competitionsByCompetitionsId) {
        this.competitionsByCompetitionsId = competitionsByCompetitionsId;
    }

    @ManyToOne
    @JoinColumn(name = "horses_id", referencedColumnName = "id", nullable = false)
    public Horses getHorsesByHorsesId() {
        return horsesByHorsesId;
    }

    public void setHorsesByHorsesId(Horses horsesByHorsesId) {
        this.horsesByHorsesId = horsesByHorsesId;
    }
}
