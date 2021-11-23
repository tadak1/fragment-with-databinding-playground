package com.example.fragmentwithdatabindingplayground.ui.list_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentwithdatabindingplayground.R

class ExampleAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<ExampleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.list_view_item_row_title)
        val description: TextView = view.findViewById(R.id.list_view_item_row_description)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_view_item_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = "Title"
        viewHolder.description.text =
            "Description Description Description Description Description Description Description Description Description Description Description Description"
    }

    override fun getItemCount() = 30
}
