package com.example.savemoment.data.repository

import androidx.lifecycle.LiveData
import com.example.savemoment.data.db.room.MomentsDatabase
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository

class MomentsRepositoryImpl(private val momentDb: MomentsDatabase) : MomentsRepository {

    override suspend fun saveMoment(moment: Moment) {
        return momentDb.momentsDao().saveMoment(moment)
    }

    override suspend fun updateMoment(id: Long, title: String, description: String, picture: String) {
        return momentDb.momentsDao().updateMomentById(id, title, description, picture)
    }

    override suspend fun deleteMoment(moment: Moment) {
        return momentDb.momentsDao().deleteMoment(moment)
    }


    override fun getMoments(): LiveData<List<Moment>> {
        return momentDb.momentsDao().getAllMoments()
    }
}