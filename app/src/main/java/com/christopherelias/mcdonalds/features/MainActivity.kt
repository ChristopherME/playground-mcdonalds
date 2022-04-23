package com.christopherelias.mcdonalds.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.christopherelias.mcdonalds.R
import com.christopherelias.mcdonalds.databinding.ActivityMainBinding
import com.christopherelias.mcdonalds.features.menu.ui.MenuFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addMenuFragment()
    }

    private fun addMenuFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MenuFragment(), "MenuFragment")
            .commit()

    }
}