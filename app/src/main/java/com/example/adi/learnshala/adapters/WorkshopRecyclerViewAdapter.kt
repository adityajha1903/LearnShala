package com.example.adi.learnshala.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.adi.learnshala.R
import com.example.adi.learnshala.databinding.RecyclerViewWorkshopItemBinding
import com.example.adi.learnshala.db.WorkshopEntity

class WorkshopRecyclerViewAdapter(
    private var context: Context,
    private var workshopList: ArrayList<WorkshopEntity>,
    private val isAppliedList: Boolean,
    private val appliedListener: (workshop: WorkshopEntity) -> Unit
): RecyclerView.Adapter<WorkshopRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(binding: RecyclerViewWorkshopItemBinding): RecyclerView.ViewHolder(binding.root) {
        val card = binding.card
        val title = binding.titleTV
        val org = binding.organisationTV
        val from = binding.fromTV
        val to = binding.toTV
        val applyBtn = binding.applyBtn
        val description = binding.descriptionTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerViewWorkshopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workshop = workshopList[position]
        holder.card.setCardBackgroundColor(when (workshop.id) {
            0,6,12 -> ResourcesCompat.getColor(context.resources, R.color.pink, null)
            1,7,13 -> ResourcesCompat.getColor(context.resources, R.color.green, null)
            2,8,14 -> ResourcesCompat.getColor(context.resources, R.color.light_blue, null)
            3,9 -> ResourcesCompat.getColor(context.resources, R.color.red, null)
            4,10 -> ResourcesCompat.getColor(context.resources, R.color.orange, null)
            else -> ResourcesCompat.getColor(context.resources, R.color.dark_blue, null)
        })
        holder.card.radius = 30f

        holder.title.text = workshop.title
        holder.org.text = "by ${workshop.organisation}"
        holder.from.text = workshop.from
        holder.to.text = workshop.to
        holder.description.text = workshop.description
        if (isAppliedList) {
            holder.applyBtn.text = context.resources.getString(R.string.applied)
            holder.applyBtn.background = ResourcesCompat.getDrawable(context.resources, R.drawable.button_background_applied, null)
            holder.applyBtn.isClickable = false
            holder.applyBtn.isFocusable = false
        } else {
            holder.applyBtn.text = context.resources.getString(R.string.apply)
            holder.applyBtn.background = ResourcesCompat.getDrawable(context.resources, R.drawable.button_background, null)
            holder.applyBtn.isClickable = true
            holder.applyBtn.isFocusable = true

            holder.applyBtn.setOnClickListener {
                appliedListener.invoke(workshop)
            }
        }
    }

    override fun getItemCount(): Int {
        return workshopList.size
    }
}