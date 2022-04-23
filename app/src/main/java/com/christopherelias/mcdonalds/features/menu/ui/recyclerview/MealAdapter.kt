package com.christopherelias.mcdonalds.features.menu.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.christopherelias.mcdonalds.databinding.ItemMealBinding
import com.christopherelias.mcdonalds.features.menu.models.MealUi

class MealAdapter(
    private val meals: List<MealUi>,
    val onMealClicked: (meal: MealUi) -> Unit
) : RecyclerView.Adapter<MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val holder = MealViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
        holder.binding.root.setOnClickListener {
            onMealClicked(meals[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(meals[position])
    }

    override fun getItemCount(): Int = meals.size

}

class MealViewHolder(
    val binding: ItemMealBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: MealUi) {
        binding.mealName.text = model.name
        binding.mealImage.load(model.imageUrl)
    }

}