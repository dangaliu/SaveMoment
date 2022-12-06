package com.example.savemoment.presentation.ui.moments_list.view

import android.view.View
import com.example.savemoment.domain.model.Moment

interface MomentMenuListener {
    fun  onLongClick(moment: Moment, view: View)
}