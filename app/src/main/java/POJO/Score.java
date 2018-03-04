package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by servabo on 28/02/2018.
 */
@Entity(tableName = "Scores")
public class Score implements Comparable<Score>{
    @PrimaryKey
            @NonNull
    String name;
    String scoring;
    String longitude;
    String latitude;

    public Score() {
    }

    public Score (String name, String scoring) {
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
