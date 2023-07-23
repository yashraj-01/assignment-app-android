package com.example.assignmentapplication

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        adapter = UserRecyclerViewAdapter(this@MainActivity)
        binding.rvUser.adapter = adapter
        binding.rvUser.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        mainActivityViewModel.users.observe(this) {
            Log.d("YASH from Observe", it.toString())
            adapter.setUserList(it)
        }

        binding.tvDelete.setOnDragListener { view, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    val item = event.clipData.getItemAt(0)
                    val index = Integer.parseInt(item.text.toString())
                    mainActivityViewModel.deleteUser(index)
                    Log.d("YASH on DROP", item.text as String)
                }
            }
            return@setOnDragListener true
        }
    }
}