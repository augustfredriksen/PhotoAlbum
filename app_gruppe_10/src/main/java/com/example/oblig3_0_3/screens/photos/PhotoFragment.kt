package com.example.oblig3_0_3.screens.photos

import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.oblig3_0_3.R
import com.example.oblig3_0_3.databinding.FragmentPhotoBinding
import com.example.oblig3_0_3.repository.Repository


class PhotoFragment : Fragment() {

    val args: PhotoFragmentArgs by navArgs()

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PhotoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        }

        val thumbnailId = args.thumbnailId
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)


        val repository = Repository()
        val viewModelFactory = PhotoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PhotoViewModel::class.java]
        viewModel.getCustomPhoto(thumbnailId)

        viewModel.myCustomPhoto.observe(viewLifecycleOwner, Observer { response ->
            if (response!!.isSuccessful) {

                if (binding.editTitle!!.text.toString().isEmpty()) {
                    viewModel.getCustomTitle(response.body()!!.title)
                }
                else {
                    viewModel.getCustomTitle(response.body()!!.title)
                    response.body()!!.title = binding.editTitle!!.text.toString()
                }


                Log.d("Response", response.body().toString())
                val imgUrl = response.body()?.url
                binding.photoTitle!!.text = response.body()?.title
                Log.d("Response", response.code().toString())
                response.body()?.let { Log.d("Response", it.thumbnailUrl.plus(".jpg")) }
                loadImage(imgUrl!!)
                viewModel.getCustomTitle(response.body()!!.title)
                Log.d("Response", imgUrl)

                binding.changeTitleButton?.setOnClickListener {
                    viewModel.getCustomTitle(binding.editTitle!!.text.toString())
                    response.body()!!.title = viewModel.myCustomTitle.value.toString()
                    viewModel.changeTitlePhoto(thumbnailId, response.body()!!)
                    Log.d("Response", response.code().toString())
                    Log.d("Response", response.body().toString())
                    Log.d("Response", response.message().toString())
                }

                binding.deletePhoto!!.setOnClickListener {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    builder.setCancelable(true)
                    builder.setTitle("Delete Photo")
                    builder.setMessage("Delete ${response.body()!!.title}?")
                    builder.setPositiveButton("Confirm",
                        DialogInterface.OnClickListener { dialog, which ->
                            viewModel.deletePhoto(thumbnailId)
                            Toast.makeText(context, "Photo deleted successfully!", Toast.LENGTH_SHORT).show()
                            Log.d("Response", response.code().toString())
                            Log.d("Response", response.body().toString())
                            Log.d("Response", response.message().toString())
                            findNavController().navigateUp()
                        })
                    builder.setNegativeButton(android.R.string.cancel,
                        DialogInterface.OnClickListener { dialog, which -> })

                    val dialog: AlertDialog = builder.create()
                    dialog.show()

                }

            } else {
                Log.d("Response", response.code().toString())
            }
        })
        return binding.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
    }

    fun loadImage(imgUrl: String) {
        val url = GlideUrl(
            imgUrl, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(requireContext()))
                .build()
        )
            Glide.with(this)
                .load(url)
                .override(900, 900)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(binding.imageView)
        }


/*        Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_baseline_broken_image_24)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    // log exception
                    Log.e("TAG", "Error loading image", e)
                    return false // important to return false so the error placeholder can be placed
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(binding.imageView)*/

    }

