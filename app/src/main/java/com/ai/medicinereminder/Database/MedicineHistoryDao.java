package com.ai.medicinereminder.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicineHistoryDao {

    @Query("SELECT * FROM MedicineHistory_table")
    List <MedicineHistory> getMedicineHistoryList();

    @Insert
    void insertMedicineHistory (MedicineHistory medicineHistory);

    @Update
    void updateMedicineHistory (MedicineHistory medicineHistory);

    @Delete
    void deleteMedicineHistory (MedicineHistory medicineHistory);

}
