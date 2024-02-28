package me.ajith.learning.roomsample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.ajith.learning.roomsample.R
import me.ajith.learning.roomsample.common.DatabaseBuilder
import me.ajith.learning.roomsample.common.ViewModelFactory
import me.ajith.learning.roomsample.data.User
import me.ajith.learning.roomsample.data.UserAdapter
import me.ajith.learning.roomsample.data.local.DatabaseHelperImpl
import me.ajith.learning.roomsample.viewmodel.MainViewModel

class MainFragment:Fragment() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var mainViewModel: MainViewModel

    companion object{
        const val TAG = "MainFragment"

        fun newInstance(): MainFragment {
            val mainFragment = MainFragment()
            val bundle = Bundle()
            mainFragment.arguments = bundle
            return mainFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupUserAdapter()
        setupViewModel()
        setupObserver()
    }

    private fun setupView(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
    }

    private fun setupUserAdapter(){
        userAdapter = UserAdapter(arrayListOf())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = userAdapter
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(requireActivity(),ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getDbInstance(requireContext().applicationContext))))[MainViewModel::class.java]
    }

    private fun setupObserver(){
        mainViewModel.getUserData().observe(viewLifecycleOwner){
            if (it != null) {
                userAdapter.addUsers(it)
                userAdapter.notifyDataSetChanged()
            }
        }
    }

}