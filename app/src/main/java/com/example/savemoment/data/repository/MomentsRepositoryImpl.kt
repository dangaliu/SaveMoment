package com.example.savemoment.data.repository

import androidx.lifecycle.LiveData
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository

class MomentsRepositoryImpl(
    private val momentStorage: MomentStorage
) : MomentsRepository {

    override suspend fun saveMoment(moment: Moment) {
        momentStorage.save(moment)
    }

    override suspend fun updateMoment(
        id: Long,
        title: String,
        description: String,
        picture: String
    ) {
        momentStorage.updateMoment(id, title, description, picture)
    }

    override suspend fun deleteMoment(moment: Moment) {
        momentStorage.deleteMoment(moment)
    }


    override fun getMoments(): LiveData<List<Moment>> {
        return momentStorage.getMoments()
    }
}