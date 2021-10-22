package com.ai.medicinereminder.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Medicine_table")
public class Medicine implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int medicineID;

    @NonNull
    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "MedicineType")
    private int medicineType; // 1 == tablet ;;; 2 == syrup ;;; 3 == dropper ;;; 4 === injection

    @NonNull
    @ColumnInfo(name = "MedicineQuantity")
    private float medicineQuantity;

    @ColumnInfo(name = "SyrupType")
    private int syrupType;

    @NonNull
    @ColumnInfo(name = "DurationType") // 1 == day ;;; 2 == month
    private int durationType;

    @NonNull
    @ColumnInfo(name = "Duration")
    private int duration;

    @NonNull
    @ColumnInfo(name = "BaseType") // 1 == hourly ;;; 2 == session
    private int baseType;

    @ColumnInfo(name = "TotalHour")
    private int totalHour;

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

    @ColumnInfo(name = "ConsumeTime")
    private int consumeTime;

    @ColumnInfo(name = "Description")
    private String description;

    public Medicine() {

    }

    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(int medicineType) {
        this.medicineType = medicineType;
    }

    public float getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(float medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public int getSyrupType() {
        return syrupType;
    }

    public void setSyrupType(int syrupType) {
        this.syrupType = syrupType;
    }

    public int getDurationType() {
        return durationType;
    }

    public void setDurationType(int durationType) {
        this.durationType = durationType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getBaseType() {
        return baseType;
    }

    public void setBaseType(int baseType) {
        this.baseType = baseType;
    }

    public int getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(int totalHour) {
        this.totalHour = totalHour;
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

    public int getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(int consumeTime) {
        this.consumeTime = consumeTime;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

}
