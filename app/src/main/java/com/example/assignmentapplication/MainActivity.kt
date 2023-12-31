package com.example.assignmentapplication

import android.os.Bundle
import android.view.DragEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignmentapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DeleteDialogFragment.DeleteDialogListener {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserRecyclerViewAdapter
    private var itemToDelete = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        adapter = UserRecyclerViewAdapter(this@MainActivity)
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        mainActivityViewModel.users.observe(this) {
            adapter.setUserList(it)
        }

        binding.tvDelete.setOnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val index = Integer.parseInt(item.text.toString())
                    itemToDelete = index
                    val deleteDialog = DeleteDialogFragment(mainActivityViewModel.getUser(index))
                    deleteDialog.show(supportFragmentManager, "DeleteDialogFragment")
                }
            }
            return@setOnDragListener true
        }
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        if (itemToDelete == -1) throw IllegalArgumentException("Invalid item")
        else {
            mainActivityViewModel.deleteUser(itemToDelete)
            itemToDelete = -1
        }
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        itemToDelete = -1
        dialog.dialog?.cancel()
    }
}