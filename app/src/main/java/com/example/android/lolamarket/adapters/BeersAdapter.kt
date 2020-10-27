package com.example.android.lolamarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lolamarket.R
import com.example.android.lolamarket.models.Beers
import kotlinx.android.synthetic.main.style_row_layout.view.*

/**
 * Pass the ArrayList and a listener, and add a variable to hold your data.
 */
class BeersAdapter (private val beersList : ArrayList<Beers.Beer>, private val listener : Listener) :
        RecyclerView.Adapter<BeersAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(beer : Beers.Beer)
    }

    /**
     * Bind the ViewHolder. Pass the position where each item should be displayed.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beersList[position], listener, position)
    }

    /**
     * Get the number of items to display.
     */
    override fun getItemCount(): Int = beersList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.style_row_layout, parent,
                false)
        return ViewHolder(view)
    }

    /**
     * ViewHolder class for RecyclerView items.
     */
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        // Assign values from the data model to their corresponding Views.
        fun bind(beer: Beers.Beer, listener: Listener, position: Int) {
            // Listen for user input events.
            itemView.setOnClickListener{ listener.onItemClick(beer) }
            itemView.style_name.text = beer.name
        }

    }

}