package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by servabo on 28/02/2018.
 */
@Entity(tableName = "Scores")
public class Score implements Comparable<Score>{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "scoring")
    private String scoring;
    private String longitude;
    private String latitude;

    @Ignore
    public Score() {
    }

    public Score (@NonNull String name, @NonNull String scoring) {
        this.name = name;
        this.scoring = scoring;
    }


    @Override
    public int compareTo(Score o) {

        if (Integer.parseInt(this.getScoring()) > Integer.parseInt(o.getScoring())) {
            return 1;
        }
        else if (Integer.parseInt(this.getScoring()) < Integer.parseInt(o.getScoring())) {
            return -1;
        }
        else {
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScoring() {
        return scoring;
    }

    public void setScoring(String scoring) {
        this.scoring = scoring;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
