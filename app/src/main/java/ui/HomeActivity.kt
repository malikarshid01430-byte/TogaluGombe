package com.example.togalugombe.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.togalugombe.R
import com.example.togalugombe.data.AppDatabase
import com.example.togalugombe.data.Puppet
import com.example.togalugombe.adapter.PuppetAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup featured puppets list
        val db = AppDatabase.getDatabase(this)
        var featuredPuppets = db.puppetDao().getAll()

        if (featuredPuppets.isEmpty()) {
            // Add sample data if empty
            val samplePuppets = arrayOf(
                Puppet(0, "Rama", "Protagonist", "The hero of Ramayana", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6n-Z_3zVv0N6B_XpE5o5_7_3_7_3_7_3_7_3_7_3"),
                Puppet(0, "Ravana", "Antagonist", "The demon king of Lanka", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_z8_3_7_3_7_3_7_3_7_3_7_3_7_3_7_3_7_3")
            )
            db.puppetDao().insertAll(*samplePuppets)
            featuredPuppets = db.puppetDao().getAll()
        }
        
        val featuredRecyclerView = findViewById<RecyclerView>(R.id.featuredRecyclerView)
        featuredRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        featuredRecyclerView.adapter = PuppetAdapter(featuredPuppets, isHorizontal = true)

        // Quick Actions
        findViewById<CardView>(R.id.scanCard).setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }

        findViewById<CardView>(R.id.galleryCard).setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_home
        
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_gallery -> {
                    startActivity(Intent(this, GalleryActivity::class.java))
                    true
                }
                R.id.nav_scan -> {
                    startActivity(Intent(this, ScanActivity::class.java))
                    true
                }
                R.id.nav_saved -> {
                    startActivity(Intent(this, SavedActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> true
            }
        }
    }
}
