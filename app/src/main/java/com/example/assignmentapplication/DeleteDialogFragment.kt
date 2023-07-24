package com.example.assignmentapplication

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.assignmentapplication.databinding.DialogLayoutBinding
import com.example.assignmentapplication.model.User

class DeleteDialogFragment(private val user: User) : DialogFragment() {
    private lateinit var binding: DialogLayoutBinding
    private lateinit var listener: DeleteDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        binding = DialogLayoutBinding.inflate(LayoutInflater.from(context))
        binding.user = user
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                    listener.onDialogPositiveClick(this)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                    listener.onDialogNegativeClick(this)
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as DeleteDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString()) + "must implement DeleteDialogFragment")
        }
    }


    interface DeleteDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
}