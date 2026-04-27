package com.example.togalugombe.adapter

import android.view.*
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.togalugombe.R
import com.example.togalugombe.data.AppDatabase
import com.example.togalugombe.data.Puppet

class PuppetAdapter(private var list: List<Puppet>, private val isHorizontal: Boolean = false) :
    RecyclerView.Adapter<PuppetAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
        val saveIcon: ImageView = view.findViewById(R.id.saveIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_puppet, parent, false)
        
        if (!isHorizontal) {
            view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        }
        
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val puppet = list[position]
        holder.name.text = puppet.name

        Glide.with(holder.itemView.context)
            .load(puppet.image)
            .placeholder(R.drawable.ic_gallery)
            .into(holder.image)
            
        // Toggle saved icon color
        if (puppet.isSaved) {
            holder.saveIcon.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.purple_500))
        } else {
            holder.saveIcon.setColorFilter(ContextCompat.getColor(holder.itemView.context, android.R.color.darker_gray))
        }
        
        holder.saveIcon.setOnClickListener {
            val db = AppDatabase.getDatabase(holder.itemView.context)
            val updatedPuppet = puppet.copy(isSaved = !puppet.isSaved)
            db.puppetDao().update(updatedPuppet)
            
            // Refresh list (simplified)
            list = if (isHorizontal) db.puppetDao().getAll() else db.puppetDao().getAll() // Adjust if needed
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = list.size
}