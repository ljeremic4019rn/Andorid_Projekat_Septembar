package com.example.projekat_septembar.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.projekat_septembar.R
import com.example.projekat_septembar.databinding.ActivityMainBinding
import com.example.projekat_septembar.presentation.view.recycler.adapter.BottomNavPageAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager



    override fun onCreate(savedInstanceState: Bundle?) {//setupujemo drawer navigaciju
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)


        initViewPager()
        initNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {//stavljamo onclick za gornje desno logout dugme
        R.id.logoutBtn -> {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
            true
        }
        else -> false
    }

    private fun initViewPager() {
        viewPager = findViewById(R.id.viewPager)
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = BottomNavPageAdapter(supportFragmentManager)
    }

    private fun initNavigation() {
        (findViewById<View>(R.id.bottomNavigation) as BottomNavigationView).setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.navigation_1 -> viewPager.setCurrentItem(BottomNavPageAdapter.FRAG_1_NEWEST, false)
                R.id.navigation_2 -> viewPager.setCurrentItem(BottomNavPageAdapter.FRAG_2_SEARCH, false)
                R.id.navigation_3 -> viewPager.setCurrentItem(BottomNavPageAdapter.FRAG_3_SAVED, false)
            }
            true
        }
    }


}