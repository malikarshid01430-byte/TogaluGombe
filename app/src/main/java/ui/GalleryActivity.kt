package com.example.togalugombe.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.togalugombe.R
import com.example.togalugombe.data.AppDatabase
import com.example.togalugombe.data.Puppet
import com.example.togalugombe.adapter.PuppetAdapter

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val db = AppDatabase.getDatabase(this)
        var puppets = db.puppetDao().getAll()

        if (puppets.isEmpty()) {
            // Add sample data if empty
            val samplePuppets = arrayOf(
                Puppet(0, "Rama", "Protagonist", "The hero of Ramayana", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6n-Z_3zVv0N6B_XpE5o5_7_3_7_3_7_3_7_3_7_3"),
                Puppet(0, "Ravana", "Antagonist", "The demon king of Lanka", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_z8_3_7_3_7_3_7_3_7_3_7_3_7_3_7_3_7_3")
            )
            db.puppetDao().insertAll(*samplePuppets)
            puppets = db.puppetDao().getAll()
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = PuppetAdapter(puppets, isHorizontal = false)
    }
}