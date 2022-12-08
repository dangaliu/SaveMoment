package com.example.savemoment.data.storage.db.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.domain.model.Moment

class FirebaseFireStoreStorage: MomentStorage {
    override suspend fun save(moment: Moment) {

    }

    override suspend fun updateMoment(
        id: Long,
        title: String,
        description: String,
        picture: String
    ) {

    }

    override suspend fun deleteMoment(moment: Moment) {

    }

    override fun getMoments(): LiveData<List<Moment>> {
        return MutableLiveData<List<Moment>>(listOf())
    }

}