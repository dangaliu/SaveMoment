package com.example.savemoment.data.storage

import androidx.lifecycle.LiveData
import com.example.savemoment.domain.model.Moment

interface MomentStorage {

    suspend fun save(moment: Moment)

    suspend fun updateMoment(id: Long, title: String, description: String, picture: String)

    suspend fun deleteMoment(moment: Moment)

    fun getMoments(): LiveData<List<Moment>>

}