package pl.piasek.ihaa.db;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasek.ihaa.model.Runs;
import pl.piasek.ihaa.model.Starts;
import pl.piasek.ihaa.model.Tracks;

public interface RunsRepo extends JpaRepository<Runs, Long> {
    boolean existsRunsByStartsByStartsIdAndTracksByTracksIdAndRunNumber(Starts startsByStartsId, Tracks TracksByTracksId, int runNumber);
    Runs findRunsByStartsByStartsIdAndTracksByTracksIdAndRunNumber(Starts startsByStartsId, Tracks TracksByTracksId, int runNumber);
}
