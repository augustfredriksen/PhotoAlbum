package com.example.oblig3_0_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_0_3.R
import com.example.oblig3_0_3.databinding.RowLayoutUserBinding
import com.example.oblig3_0_3.model.User

class UserAdapter(val listener : MyOnClickListener): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    var userList = emptyList<User>()



    inner class UserViewHolder(var binding: RowLayoutUserBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = RowLayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder) {

        }
    }

    fun setData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }
}