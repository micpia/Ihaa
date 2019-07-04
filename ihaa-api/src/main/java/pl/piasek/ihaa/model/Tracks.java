package pl.piasek.ihaa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tracks {
    private int id;
    private int name;
    private int timeLimit;
    private Styles stylesByStylesId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public int getName() {
        return name;
    }

    public void setName(int name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tracks tracks = (Tracks) o;
        return id == tracks.id &&
                name == tracks.name &&
                timeLimit == tracks.timeLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, timeLimit);
    }

    @ManyToOne
    @JoinColumn(name = "styles_id", referencedColumnName = "id", nullable = false)
    public Styles getStylesByStylesId() {
        return stylesByStylesId;
    }

    public void setStylesByStylesId(Styles stylesByStylesId) {
        this.stylesByStylesId = stylesByStylesId;
    }
}
