package com.example.assignmentapplication

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmentapplication.databinding.ItemLayoutBinding
import com.example.assignmentapplication.model.User
import com.google.android.material.card.MaterialCardView


class UserRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    private var userList: List<User> = emptyList()
    private lateinit var binding: ItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = ItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)

        binding.cvUser.setOnLongClickListener {
            val item = ClipData.Item(position.toString() as CharSequence)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val dragData = ClipData(user.first_name, mimeTypes, item)
            val shadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(dragData, shadowBuilder, null, 0)
        }
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList
        Log.d("YASH from setUserList", "Hi "+this.userList.toString())
        notifyDataSetChanged()
    }


    class UserViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
        }
    }

}