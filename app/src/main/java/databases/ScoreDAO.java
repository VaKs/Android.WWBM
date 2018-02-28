package databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import POJO.Score;

/**
 * Created by servabo on 28/02/2018.
 */
@Dao
public interface ScoreDAO {

    // Get all quotations from the database
    @Query("SELECT * FROM Scores")
    List<Score> getQuotations();

    // Add a given quotation to the database
    @Insert
    void addScore(Score score);

    // Delete all
    @Query("DELETE FROM Scores")
    void clearScore();
}
