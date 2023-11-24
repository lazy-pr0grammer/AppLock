package com.aylax.applock.ui.themes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aylax.applock.databinding.ItemThemesBinding
import com.aylax.library.model.Theme

class ThemesAdapter(private val theme: List<Theme>) :
    RecyclerView.Adapter<ThemesAdapter.ViewHolder>() {

    class ViewHolder(private var binding: ItemThemesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(theme: Theme) {
            binding.root.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemThemesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return theme.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(theme[position])
    }
}