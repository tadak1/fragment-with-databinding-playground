package com.example.fragmentwithdatabindingplayground.ui.list_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentwithdatabindingplayground.R
import dagger.hilt.android.AndroidEntryPoint

class ListViewFragment : Fragment(R.layout.list_view_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_view_fragment, container, false)?.apply {
            val listView = findViewById<RecyclerView>(R.id.list_view)
            val linearLayoutManager = LinearLayoutManager(context)
            val adapter = ExampleAdapter(dataSet = arrayOf("test"))
            val headerAdapter = ExampleHeaderAdapter()


            listView.layoutManager = linearLayoutManager
            listView.adapter = ConcatAdapter(headerAdapter, adapter)
        }
    }
}
