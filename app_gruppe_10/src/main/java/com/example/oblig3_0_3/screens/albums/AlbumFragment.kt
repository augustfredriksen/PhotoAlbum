package com.example.oblig3_0_3.screens.albums

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_0_3.adapter.AlbumAdapter
import com.example.oblig3_0_3.adapter.MyOnClickListener
import com.example.oblig3_0_3.databinding.FragmentAlbumBinding
import com.example.oblig3_0_3.repository.Repository


class AlbumFragment : Fragment(), MyOnClickListener {

    val args: AlbumFragmentArgs by navArgs()

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AlbumViewModel
    private val albumAdapter by lazy { AlbumAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val userId = args.userId

        setHasOptionsMenu(true)



        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = AlbumViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[AlbumViewModel::class.java]
        viewModel.getCustomAlbums(userId)
        viewModel.myCustomAlbums.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { albumAdapter.setData(it) }
            }
            else {
                Log.d("Response", response.code().toString())
            }


        })
        return binding.root

    }

    private fun setupRecyclerView() {
        binding.albumList.adapter = albumAdapter
        binding.albumList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    override fun onClick(position: Int) {
        var albumId = albumAdapter.albumList[position].id
        val action = AlbumFragmentDirections.actionAlbumFragmentToThumbnailFragment(albumId)
        view?.findNavController()?.navigate(action)

        Log.d("Response", albumAdapter.albumList[position].title)
    }
}