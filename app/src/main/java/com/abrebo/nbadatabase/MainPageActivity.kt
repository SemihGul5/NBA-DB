package com.abrebo.nbadatabase

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.abrebo.nbadatabase.databinding.ActivityMainPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navigationView,navHostFragment.navController)

        val toggle = ActionBarDrawerToggle(this,
            binding.drawer,
            binding.toolbar,
            0,
            0)

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }


}
