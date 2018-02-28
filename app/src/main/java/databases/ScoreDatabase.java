package databases;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import POJO.Score;

/**
 * Created by servabo on 28/02/2018.
 */

@Database(version = 1, entities = {Score.class})
public abstract class ScoreDatabase extends RoomDatabase {

    // Singleton pattern to centralize access to the database
    private static ScoreDatabase database;

    public synchronized static ScoreDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, ScoreDatabase.class, "WWBM_db")
                    .allowMainThreadQueries() // This should only be used for testing
                    .build();
        }
        return database;
    }

    // Provides access to available operations on the database
    public abstract ScoreDAO scoreDAO();
}
