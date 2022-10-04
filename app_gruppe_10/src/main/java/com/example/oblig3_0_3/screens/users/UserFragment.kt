package com.example.oblig3_0_3.screens.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oblig3_0_3.adapter.MyOnClickListener
import com.example.oblig3_0_3.adapter.UserAdapter
import com.example.oblig3_0_3.adapter.UserDbAdapter
import com.example.oblig3_0_3.databinding.FragmentUserBinding
import com.example.oblig3_0_3.model.User
import com.example.oblig3_0_3.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserFragment : Fragment(), MyOnClickListener{

    private val viewModel: UserViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel: UserDbViewModel
    private val userAdapter by lazy { UserAdapter(this) }
    private val userDbAdapter by lazy { UserDbAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater, container, false)

        setupRecyclerView()

        val repository = Repository()
        mUserViewModel = ViewModelProvider(this)[UserDbViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            userDbAdapter.setData(user)
        })
        viewModel.getUsers()
        viewModel.myUsers.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { userAdapter.setData(it) }
            }
            else {
                Log.d("Response", response.code().toString())
            }
        })

        binding.refreshButton.setOnClickListener {
            GlobalScope.launch {

                deleteDataFromDatabase()
                insertDataToDatabase()
            }


        }

        return binding.root

    }

    private fun setupRecyclerView() {
        binding.userList.adapter = userDbAdapter
        binding.userList.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {
        var userId = userDbAdapter.dbUserList[position].id
        val action = UserFragmentDirections.actionUserFragmentToAlbumFragment(userId)
        view?.findNavController()?.navigate(action)

        Log.d("Response", userDbAdapter.dbUserList[position].name)
    }


    private suspend fun insertDataToDatabase() {
        delay(1000)
        for (i in 0 until userAdapter.userList.size) {
            var id = userAdapter.userList[i].id
            var name = userAdapter.userList[i].name
            var email = userAdapter.userList[i].email

            var user = User(id, name, email)

            mUserViewModel.addUser(user)
        }
    }

    private fun deleteDataFromDatabase() {
        for (i in 0 until userDbAdapter.dbUserList.size) {
            var id = userDbAdapter.dbUserList[i].id
            var name = userDbAdapter.dbUserList[i].name
            var email = userDbAdapter.dbUserList[i].email

            var user = User(id, name, email)

            mUserViewModel.deleteUser(user)

        }
    }
}
