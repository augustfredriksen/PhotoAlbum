package com.example.oblig3_0_3.screens.thumbnails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_0_3.adapter.MyOnClickListener
import com.example.oblig3_0_3.adapter.ThumbnailAdapter
import com.example.oblig3_0_3.databinding.FragmentThumbnailBinding
import com.example.oblig3_0_3.repository.Repository

class ThumbnailFragment : Fragment(), MyOnClickListener {

    val args: ThumbnailFragmentArgs by navArgs()

    private var _binding: FragmentThumbnailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ThumbnailViewModel
    private val thumbnailAdapter by lazy { ThumbnailAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThumbnailBinding.inflate(inflater, container, false)
        val albumId = args.albumId

        setupRecyclerView()
        setHasOptionsMenu(true)

        val repository = Repository()
        val viewModelFactory = ThumbnailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ThumbnailViewModel::class.java]
        viewModel.getCustomThumbnails(albumId)
        viewModel.myCustomThumbnails.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                response.body()?.let { thumbnailAdapter.setData(it) }
            } else {
                Log.d("Response", response.code().toString())
            }


        }
        return binding.root

    }

    private fun setupRecyclerView() {
        binding.photoList.adapter = thumbnailAdapter
        binding.photoList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onClick(position: Int) {
        var thumbnailId = thumbnailAdapter.thumbnailList[position].id
        val action = ThumbnailFragmentDirections.actionThumbnailFragmentToPhotoFragment(thumbnailId)
        view?.findNavController()?.navigate(action)
    }


}