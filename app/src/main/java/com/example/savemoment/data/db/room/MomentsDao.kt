package com.example.savemoment.data.db.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.savemoment.domain.model.Moment

@Dao
interface MomentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMoment(moment: Moment)

    @Delete
    suspend fun deleteMoment(moment: Moment)

    @Query("select * from moments")
    fun getAllMoments(): LiveData<List<Moment>>

    @Query("update moments set title = :title, description = :description, picture = :picture where id = :id")
    suspend fun updateMomentById(id: Long, title: String, description: String, picture: String)

}