package com.example.assignmentapplication

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentapplication.databinding.ItemLayoutBinding
import com.example.assignmentapplication.model.User


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
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }


    class UserViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnLongClickListener {
        fun bind(user: User) {
            binding.user = user
            binding.cvUser.setOnLongClickListener(this)
        }

        override fun onLongClick(view: View?): Boolean {
            val item = ClipData.Item(layoutPosition.toString() as CharSequence)
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val dragData = ClipData(layoutPosition.toString(), mimeTypes, item)
            val shadowBuilder = View.DragShadowBuilder(view)
            view?.startDragAndDrop(dragData, shadowBuilder, null, 0)
            return true
        }
    }
}