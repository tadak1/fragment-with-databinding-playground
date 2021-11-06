package com.example.fragmentwithdatabindingplayground.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentwithdatabindingplayground.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            return AlertDialog.Builder(it).setMessage("OK?")
                .setPositiveButton(
                    "Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        findNavController().navigate(R.id.action_confirm_dialog_fragment_to_main_fragment)
                    }
                )
                .setNegativeButton(
                    "Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                    }
                ).create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}

class ExampleBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = layoutInflater.inflate(R.layout.bottom_sheet, container, false)
        layout.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            findNavController().navigate(ExampleBottomSheetDirections.actionExampleBottomSheetToMainFragment())
        }
        return layout.rootView
    }
}
