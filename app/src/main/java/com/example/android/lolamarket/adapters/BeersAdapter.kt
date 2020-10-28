package com.example.android.lolamarket.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lolamarket.R
import com.example.android.lolamarket.models.Beers
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.beers_grid_layout.view.*
import java.lang.Exception

class BeersAdapter(var context: Context, var arrayList: ArrayList<Beers.Beer>) :
        RecyclerView.Adapter<BeersAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.beers_grid_layout, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        try {
            var beer: Beers.Beer = arrayList.get(position)

            holder.names.beer_name_text_view.text = beer.name
            holder.icons.fromUrl(beer.labels.icon)

            holder.itemView.setOnClickListener {
                Toast.makeText(context, beer.name, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icons = itemView.beer_icon_image
        var names = itemView.beer_name_text_view
    }

}

private fun ImageView.fromUrl(url: String) {
    try {
        Picasso.get().load(url).into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/*

/**
 * Pass the ArrayList and a listener, and add a variable to hold your data.
 */
class BeersAdapter(private val beersList: ArrayList<Beers.Beer>, private val listener: Listener) :
        RecyclerView.Adapter<BeersAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(beer: Beers.Beer)
    }

    /**
     * Bind the ViewHolder. Pass the position where each item should be displayed.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(beersList, listener, position)
    }

    /**
     * Get the number of items to display.
     */
    override fun getItemCount(): Int = beersList.count()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.beers_grid_layout, parent,
                false)
        return ViewHolder(view)
    }

    /**
     * ViewHolder class for RecyclerView items.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Assign values from the data model to their corresponding Views.
        fun bind(beers: ArrayList<Beers.Beer>, listener: Listener, position: Int) {
            try {
                // Listen for user input events.
                itemView.setOnClickListener{ listener.onItemClick(beers[position]) }
                itemView.beerName.text = "Heineken"
                //itemView.grid_view[0] = beer
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}

*/