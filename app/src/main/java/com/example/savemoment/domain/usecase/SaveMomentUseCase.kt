package com.example.savemoment.domain.usecase

import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository

class SaveMomentUseCase(private val momentsRepository: MomentsRepository) {

    suspend fun execute(moment: Moment) {
        momentsRepository.saveMoment(moment)
    }
}