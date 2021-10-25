package com.ai.medicinereminder.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MedicineHistory_table")
public class MedicineHistory {

    @PrimaryKey
    private int medicineID;

    @ColumnInfo(name = "Morning")
    private boolean morning;

    @ColumnInfo(name = "Noon")
    private boolean noon;

    @ColumnInfo(name = "Afternoon")
    private boolean afternoon;

    @ColumnInfo(name = "Evening")
    private boolean evening;

    @ColumnInfo(name = "Night")
    private boolean night;

    public MedicineHistory() {

    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isNoon() {
        return noon;
    }

    public void setNoon(boolean noon) {
        this.noon = noon;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isEvening() {
        return evening;
    }

    public void setEvening(boolean evening) {
        this.evening = evening;
    }

    public boolean isNight() {
        return night;
    }

    public void setNight(boolean night) {
        this.night = night;
    }

}
