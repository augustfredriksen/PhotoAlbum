package com.example.oblig3_0_3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.oblig3_0_3.databinding.GridLayoutAlbumBinding
import com.example.oblig3_0_3.databinding.RowLayoutUserBinding
import com.example.oblig3_0_3.model.Album
import com.example.oblig3_0_3.model.Photo

class AlbumAdapter(val listener : MyOnClickListener): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    var albumList = emptyList<Album>()

    inner class AlbumViewHolder(var binding: GridLayoutAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = GridLayoutAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        with(holder) {
//            binding.idTxt.text = albumList[position].id.toString()
//            binding.userIdTxt.text = albumList[position].userId.toString()
            binding.titleTxt.text = albumList[position].title
        }
    }

    fun setData(newList: List<Album>) {
        albumList = newList
        notifyDataSetChanged()
    }
}