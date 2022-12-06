package com.example.savemoment.presentation.ui.addMoment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.usecase.DeleteMomentUseCase
import com.example.savemoment.domain.usecase.SaveMomentUseCase
import com.example.savemoment.domain.usecase.UpdateMomentUseCase
import kotlinx.coroutines.launch

class AddMomentViewModel(
    private val deleteMomentUseCase: DeleteMomentUseCase,
    private val saveMomentUseCase: SaveMomentUseCase,
    private val updateMomentUseCase: UpdateMomentUseCase
) : ViewModel() {

    fun delete(moment: Moment) {
        viewModelScope.launch {
            deleteMomentUseCase.execute(moment)
        }
    }

    fun save(moment: Moment) {
        viewModelScope.launch {
            deleteMomentUseCase.execute(moment)
        }
    }

    fun update(moment: Moment) {
        viewModelScope.launch {
            updateMomentUseCase.execute(moment.id!!, moment.title, moment.description ?: "")
        }
    }
}