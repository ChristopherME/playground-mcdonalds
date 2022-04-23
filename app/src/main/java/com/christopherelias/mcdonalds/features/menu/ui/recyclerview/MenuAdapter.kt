package com.christopherelias.mcdonalds.features.menu.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.christopherelias.mcdonalds.databinding.ItemMenuBinding
import com.christopherelias.mcdonalds.features.menu.models.MealUi
import com.christopherelias.mcdonalds.features.menu.models.MenuUi

class MenuAdapter(
    private val items: List<MenuUi>,
    val onMealClicked: (meal: MealUi) -> Unit
) : RecyclerView.Adapter<MenuHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        return MenuHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) {
            onMealClicked(it)
        }
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}

class MenuHolder(
    val binding: ItemMenuBinding,
    val onMealClicked: (meal: MealUi) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(menu: MenuUi) {
        binding.menuTitle.text = menu.title
    }
}