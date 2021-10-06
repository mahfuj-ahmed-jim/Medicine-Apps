package com.ai.medicinereminder.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medicine.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {

    private static com.ai.medicinereminder.Database.MainDatabase INSTANCE;

    public static synchronized com.ai.medicinereminder.Database.MainDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), com.ai.medicinereminder.Database.MainDatabase.class,
                    "Medicine_table").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    /*static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE Version_table (versionNumber INT, PRIMARY KEY(userid))");
        }
    };*/

    public abstract MedicineDao medicineDao();

}