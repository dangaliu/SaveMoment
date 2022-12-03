package com.example.savemoment.domain.repository

import androidx.lifecycle.LiveData
import com.example.savemoment.domain.model.Moment

interface MomentsRepository {

    suspend fun saveMoment(moment: Moment)

    suspend fun updateMoment(id: Long, title: String, description: String)

    suspend fun deleteMoment(moment: Moment)

    fun getMoments(): LiveData<List<Moment>>
}