package com.example.fragmentwithdatabindingplayground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.fragmentwithdatabindingplayground.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).apply {
            findViewById<Button>(R.id.detail_fragment_button).apply {
                setOnClickListener {
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToExampleBottomSheet())
                }
            }
            findViewById<Button>(R.id.detail_fragment_button2).apply {
                setOnClickListener {
                    findNavController().navigate(MainFragmentDirections.actionMainFragmentToConfirmDialogFragment())
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.userDetail.observe(viewLifecycleOwner) {
            print(it)
        }
    }
}
