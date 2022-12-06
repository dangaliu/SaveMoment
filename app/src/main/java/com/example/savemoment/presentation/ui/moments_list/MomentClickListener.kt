package com.example.savemoment.presentation.ui.moments_list

import com.example.savemoment.domain.model.Moment

interface MomentClickListener {
    fun onClick(moment: Moment)
}