package com.example.savemoment.presentation.ui.moments_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.domain.repository.MomentsRepository
import com.example.savemoment.domain.usecase.DeleteMomentUseCase
import com.example.savemoment.domain.usecase.GetMomentsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MomentsViewModel(
    private val getMomentsUseCase: GetMomentsUseCase,
    private val deleteMomentUseCase: DeleteMomentUseCase
) : ViewModel() {

    private val momentsMutable = getMomentsUseCase.execute()
    val moments: LiveData<List<Moment>> = momentsMutable

    fun delete(moment: Moment) {
        viewModelScope.launch {
            deleteMomentUseCase.execute(moment)
        }
    }

}