package com.montyblank.mybooks

import android.content.res.ColorStateList
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.montyblank.mybooks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        // Criando o estado de cores programaticamente usando colors.xml
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked), // Selecionado
            intArrayOf(-android.R.attr.state_checked) // Não selecionado
        )

        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.purple_500),
            ContextCompat.getColor(this, R.color.gray)
        )

        val colorStateList = ColorStateList(states, colors)

        // Aplicando as cores ao ícone e ao texto dos botões
        navView.itemIconTintList = colorStateList
        navView.itemTextColor = colorStateList

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}