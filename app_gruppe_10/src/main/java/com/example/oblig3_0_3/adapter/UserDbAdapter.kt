package com.example.oblig3_0_3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_0_3.R
import com.example.oblig3_0_3.databinding.RowLayoutUserBinding
import com.example.oblig3_0_3.model.User
import kotlinx.android.synthetic.main.row_layout_user.view.*

class UserDbAdapter(val listener: MyOnClickListener): RecyclerView.Adapter<UserDbAdapter.UserDbViewHolder>() {

    var dbUserList = emptyList<User>()

    inner class UserDbViewHolder(var binding: RowLayoutUserBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDbAdapter.UserDbViewHolder {
        val binding = RowLayoutUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDbViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dbUserList.size
    }

    override fun onBindViewHolder(holder: UserDbViewHolder, position: Int) {
        with(holder) {
            val sp = holder.itemView.context?.let { PreferenceManager.getDefaultSharedPreferences(holder.itemView.context) }
            val emailSetting = sp?.getBoolean("email_setting", true)

            if (!emailSetting!!) {
                binding.emailTxt.isGone = true
            }
            binding.nameTxt.text = dbUserList[position].name
            binding.emailTxt.text = dbUserList[position].email
        }
    }

    fun setData(user: List<User>) {
        this.dbUserList = user
        notifyDataSetChanged()

    }

}