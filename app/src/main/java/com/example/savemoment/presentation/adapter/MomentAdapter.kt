package com.example.savemoment.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.savemoment.databinding.ItemMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.ui.moments_list.MomentClickListener
import com.example.savemoment.presentation.ui.moments_list.MomentMenuListener

class MomentAdapter(
    private val context: Context,
    private val menuListener: MomentMenuListener,
    private val onClickListener: MomentClickListener
) :
    RecyclerView.Adapter<MomentAdapter.MomentHolder>() {

    private var moments = listOf<Moment>()

    inner class MomentHolder(val binding: ItemMomentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MomentHolder {
        val binding = ItemMomentBinding.inflate(LayoutInflater.from(context), parent, false)
        return MomentHolder(binding)
    }

    override fun onBindViewHolder(holder: MomentHolder, position: Int) {
        val moment = moments[position]
        with(holder.binding) {
            tvMomentTitle.text = moment.title
            tvMomentDescription.text = moment.description
            if (moment.picture != "null") {
                Glide.with(ivMoment).load(moment.picture).into(ivMoment)
            }
            rootMomentView.setOnClickListener {
                onClickListener.onClick(moment)
            }
            rootMomentView.setOnLongClickListener {
                menuListener.onLongClick(moment, rootMomentView)
                true
            }
        }
    }

    override fun getItemCount() = moments.size

    fun updateMoments(moments: List<Moment>) {
        this.moments = moments
        notifyDataSetChanged()
    }
}