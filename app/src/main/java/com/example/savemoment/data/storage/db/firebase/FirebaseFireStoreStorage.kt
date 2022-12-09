package com.example.savemoment.data.storage.db.firebase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.domain.model.Moment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseFireStoreStorage : MomentStorage {

    private val fireStore = Firebase.firestore
    private val momentsCollection = fireStore.collection("moments")
    override suspend fun save(moment: Moment) {
        momentsCollection.add(moment)
    }

    override suspend fun updateMoment(moment: Moment) {

    }


    override suspend fun deleteMoment(moment: Moment) {

    }

    override fun getMoments(): LiveData<List<Moment>> {
        throw Exception("FirebaseException")
    }
}
