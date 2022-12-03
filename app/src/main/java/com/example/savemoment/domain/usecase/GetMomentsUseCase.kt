package com.example.savemoment.domain.usecase

import androidx.lifecycle.LiveData
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository

class GetMomentsUseCase(private val momentsRepository: MomentsRepository) {

    fun execute(): LiveData<List<Moment>> {
        return momentsRepository.getMoments()
    }
}