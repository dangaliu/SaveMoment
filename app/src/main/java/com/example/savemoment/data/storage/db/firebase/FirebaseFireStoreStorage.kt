package com.example.savemoment.data.storage.db.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.savemoment.data.storage.MomentStorage
import com.example.savemoment.domain.model.Moment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class FirebaseFireStoreStorage : MomentStorage {

    private val fireStore = Firebase.firestore
    private val momentsCollection = fireStore.collection("moments")
    override suspend fun save(moment: Moment) {
        momentsCollection.add(moment)
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
