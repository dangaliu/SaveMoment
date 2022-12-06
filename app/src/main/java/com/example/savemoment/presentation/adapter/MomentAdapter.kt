package com.example.savemoment.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.savemoment.databinding.ItemMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.moments_list.view.MomentMenuListener

class MomentAdapter(private val context: Context, private val menuListener: MomentMenuListener) :
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
            ivMoment.setImageURI("".toUri())
            tvMomentTitle.text = moment.title
            tvMomentDescription.text = moment.description
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