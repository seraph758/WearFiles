package com.dertefter.wearfiles.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.common.Utils
import com.dertefter.wearfiles.databinding.ItemThemeBinding
import com.dertefter.wearfiles.model.Action
import com.dertefter.wearfiles.model.ActionType
import com.dertefter.wearfiles.ui.adapter.FileAdapter.FileViewHolder
import java.io.File

class ThemesAdapter(
    private val onThemeSelected: (Int) -> Unit
) : RecyclerView.Adapter<ThemesAdapter.ThemeViewHolder>() {


    private var themes = mutableListOf<Int>()
    private var selectedItem: Int? = null

    fun  updateThemesList(list: List<Int>, ){
        themes = list.toMutableList()
        notifyDataSetChanged()
    }

    fun updateSelectedTheme(selected: Int? = null){
        selectedItem = selected
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeViewHolder {
            val view = LayoutInflater
                .from( ContextThemeWrapper(
                    parent.context,
                    themes[viewType]
                ))
                .inflate(R.layout.item_theme, parent, false)
            return ThemeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThemeViewHolder, position: Int) {
        holder.bind(themes[position])
    }

    override fun getItemCount(): Int = themes.size

    override fun getItemViewType(position: Int): Int = position

    inner class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemThemeBinding.bind(itemView)

        fun bind(theme: Int) {
            if (theme == selectedItem){
               binding.checked.visibility = View.VISIBLE
            }else{
               binding.checked.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                val previousSelectedItem = selectedItem
                selectedItem = theme
                notifyItemChanged(themes.indexOf(previousSelectedItem))
                onThemeSelected(theme)

            }

        }
    }
}