package com.example.savemoment.data.db.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.savemoment.domain.model.Moment

@Database(entities = [Moment::class], version = 3, exportSchema = false)
abstract class MomentsDatabase : RoomDatabase() {

    abstract fun momentsDao(): MomentsDao

    companion object {
        @Volatile
        private var instance: MomentsDatabase? = null

        fun getInstance(context: Context): MomentsDatabase {
            if (instance == null) {
                synchronized(Any()) {
                    if (instance == null) {
                        instance =
                            Room.databaseBuilder(context, MomentsDatabase::class.java, "momentsDb")
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return instance!!
        }
    }

}