package com.example.savemoment.domain.usecase

import com.example.savemoment.domain.repository.MomentsRepository

class UpdateMomentUseCase(private val momentsRepository: MomentsRepository) {

    suspend fun execute(id: Long, title: String, description: String) {
        momentsRepository.updateMoment(id, title, description)
    }
}