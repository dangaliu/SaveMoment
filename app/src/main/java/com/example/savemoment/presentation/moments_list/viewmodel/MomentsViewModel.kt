package com.example.savemoment.presentation.moments_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository
import kotlinx.coroutines.launch

class MomentsViewModel(private val momentsRepository: MomentsRepository) : ViewModel() {

    private val momentsMutable = momentsRepository.getMoments()
    val moments: LiveData<List<Moment>> = momentsMutable

    fun save(moment: Moment) {
        viewModelScope.launch {
            momentsRepository.saveMoment(moment)
        }
    }

    fun delete(moment: Moment) {
        viewModelScope.launch {
            momentsRepository.deleteMoment(moment)
        }
    }

}