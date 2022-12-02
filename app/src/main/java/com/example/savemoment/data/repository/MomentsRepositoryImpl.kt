package com.example.savemoment.data.repository

import androidx.lifecycle.LiveData
import com.example.savemoment.data.db.room.MomentsDatabase
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository

class MomentsRepositoryImpl(private val momentDb: MomentsDatabase) : MomentsRepository {

    override suspend fun saveMoment(moment: Moment): Boolean {
        return momentDb.momentsDao().saveMoment(moment)
    }

    override suspend fun updateMoment(id: Long, title: String, description: String): Boolean {
        return momentDb.momentsDao().updateMomentById(id, title, description)
    }


    override fun getMoments(): LiveData<List<Moment>> {
        return momentDb.momentsDao().getAllMoments()
    }
}