package com.example.android.lolamarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.lolamarket.R
import com.example.android.lolamarket.models.Styles
import kotlinx.android.synthetic.main.style_row_layout.view.*

/**
 * Pass the ArrayList and a listener, and add a variable to hold your data.
 */
class StylesAdapter (private val stylesList : ArrayList<Styles.Style>, private val listener : Listener) :
        RecyclerView.Adapter<StylesAdapter.ViewHolder>() {
    interface Listener {
        fun onItemClick(style : Styles.Style)
    }

    /**
     * Bind the ViewHolder. Pass the position where each item should be displayed.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stylesList[position], listener, position)
    }

    /**
     * Get the number of items to display.
     */
    override fun getItemCount(): Int = stylesList.count()
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
        fun bind(style: Styles.Style, listener: Listener, position: Int) {
            // Listen for user input events.
            itemView.setOnClickListener{ listener.onItemClick(style) }
            itemView.style_name.text = style.id + ".  " + style.name
        }
        
    }

}