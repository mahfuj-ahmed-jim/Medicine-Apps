package com.ai.medicinereminder.PageActivity;

import androidx.room.RoomDatabase;

public class Model_home_card  {

    String medicineName;
    String medicineCount;
    String medicineTime01;
    String medicineTime02;
    String medicineTime03;
    String medicineTime04;
    String medicineTime05;
    String meal;
    int medicineType;

    public Model_home_card(String medicineName, String medicineCount, String medicineTime01, String medicineTime02, String medicineTime03, String medicineTime04, String medicineTime05, String meal, int medicineType) {
        this.medicineName = medicineName;
        this.medicineCount = medicineCount;
        this.medicineTime01 = medicineTime01;
        this.medicineTime02 = medicineTime02;
        this.medicineTime03 = medicineTime03;
        this.medicineTime04 = medicineTime04;
        this.medicineTime05 = medicineTime05;
        this.meal = meal;
        this.medicineType = medicineType;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineCount() {
        return medicineCount;
    }

    public void setMedicineCount(String medicineCount){
        this.medicineCount = medicineCount;
    }

    public String getMedicineTime01() {
        return medicineTime01;
    }

    public void setMedicineTime01(String medicineTime01) {
        this.medicineTime01 = medicineTime01;
    }

    public String getMedicineTime02() {
        return medicineTime02;
    }

    public void setMedicineTime02(String medicineTime02) {
        this.medicineTime02 = medicineTime02;
    }

    public String getMedicineTime03() {
        return medicineTime03;
    }

    public void setMedicineTime03(String medicineTime03) {
        this.medicineTime03 = medicineTime03;
    }

    public String getMedicineTime04() {
        return medicineTime04;
    }

    public void setMedicineTime04(String medicineTime04) {
        this.medicineTime04 = medicineTime04;
    }

    public String getMedicineTime05() {
        return medicineTime05;
    }

    public void setMedicineTime05(String medicineTime05) {
        this.medicineTime05 = medicineTime05;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(int medicineType) {
        this.medicineType = medicineType;
    }
}
