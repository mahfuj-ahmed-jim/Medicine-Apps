package com.ai.medicinereminder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicineDao {

    @Query("SELECT * FROM Medicine_table")
    List<Medicine> getMedicineList();

    @Insert
    void insertMedicine(Medicine medicine);

    @Update
    void updateMedicine(Medicine medicine);

    @Delete
    void deleteMedicine(Medicine medicine);

}
