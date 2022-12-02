package com.example.savemoment.domain.repository

import androidx.lifecycle.LiveData
import com.example.savemoment.domain.model.Moment

interface MomentsRepository {

    suspend fun saveMoment(moment: Moment): Boolean

    suspend fun updateMoment(id: Long, title: String, description: String): Boolean

    fun getMoments(): LiveData<List<Moment>>
}