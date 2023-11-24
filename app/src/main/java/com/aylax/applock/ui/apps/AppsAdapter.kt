package com.aylax.applock.ui.apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aylax.applock.R
import com.aylax.applock.databinding.ItemAppsBinding
import com.aylax.library.model.Application
import com.google.android.material.elevation.SurfaceColors

class AppsAdapter(
    private var application: List<Application>,
    private var listener: OnClickListener
) :
    RecyclerView.Adapter<AppsAdapter.ViewHolder>() {

    class ViewHolder(private var binding: ItemAppsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(application: Application, listener: OnClickListener) {
            binding.apply {
                title.text = application.app_name
                binding.imageView.setImageDrawable(application.app_icon)
                background.setCardBackgroundColor(SurfaceColors.SURFACE_1.getColor(title.context))

                root.layoutParams = RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                background.setOnClickListener {
                    listener.onItemClicked(application)
                }
                if (application.is_locked == true) {
                    action.setImageResource(R.drawable.ic_lock)
                    lockType.text = "Protected!"
                } else {
                    action.setImageResource(R.drawable.ic_unlock)
                    lockType.text = "Not protected!"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAppsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return application.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(application[position], listener)
    }

    interface OnClickListener {
        fun onItemClicked(application: Application)
    }
}