package com.example.savemoment.data.storage.db.room

import androidx.lifecycle.LiveData
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.domain.model.Moment

class MomentsDatabaseStorage(private val momentsDb: MomentsDatabase) : MomentStorage {
    override suspend fun save(moment: Moment) {
        momentsDb.momentsDao().saveMoment(moment)
    }

    override suspend fun updateMoment(
        id: Long,
        title: String,
        description: String,
        picture: String
    ) {
        momentsDb.momentsDao().updateMomentById(id, title, description, picture)
    }

    override suspend fun deleteMoment(moment: Moment) {
        momentsDb.momentsDao().deleteMoment(moment)
    }

    override fun getMoments(): LiveData<List<Moment>> {
        return momentsDb.momentsDao().getAllMoments()
    }

}