package com.example.oblig3_0_3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.example.oblig3_0_3.R
import com.example.oblig3_0_3.databinding.GridLayoutAlbumBinding
import com.example.oblig3_0_3.databinding.GridLayoutThumbnailBinding
import com.example.oblig3_0_3.databinding.RowLayoutUserBinding
import com.example.oblig3_0_3.model.Album
import com.example.oblig3_0_3.model.Photo
import com.example.oblig3_0_3.screens.thumbnails.ThumbnailFragment
import kotlinx.coroutines.withContext


class ThumbnailAdapter(val listener : MyOnClickListener): RecyclerView.Adapter<ThumbnailAdapter.ThumbnailViewHolder>() {



    var thumbnailList = emptyList<Photo>()


    inner class ThumbnailViewHolder(var binding: GridLayoutThumbnailBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = absoluteAdapterPosition
                listener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbnailViewHolder {
        val binding = GridLayoutThumbnailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThumbnailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return thumbnailList.size
    }

    override fun onBindViewHolder(holder: ThumbnailViewHolder, position: Int) {
        with(holder) {
            val sp = holder.itemView.context?.let { PreferenceManager.getDefaultSharedPreferences(holder.itemView.context) }
            val thumbnailSetting = sp?.getBoolean("thumbnail_setting", true)

            if (!thumbnailSetting!!) {
                binding.thumbnailImageView.isGone = true
            }
            var imgUrl = thumbnailList[position].thumbnailUrl
            val url = GlideUrl(
                imgUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", WebSettings.getDefaultUserAgent(holder.itemView.context))
                    .build()
            )

            Glide.with(holder.itemView.context)
                .load(url)
                .override(150, 150)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(holder.itemView.findViewById(R.id.thumbnail_imageView))
            binding.titleText.text = thumbnailList[position].title



            //getPhotos(binding.thumbnailImageView, imgUrl)


        }
    }

    /*fun getPhotos(imgView: ImageView, imgUrl: String) {
        imgUrl.let {
            Glide.with(imgView.context)
                .load(imgUrl)
                .override(150, 150)
                .into(imgView)

        }
    }*/

    fun setData(newList: List<Photo>) {
        thumbnailList = newList
        notifyDataSetChanged()
    }
}