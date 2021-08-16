package com.rafaelmartorano.sicredichallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.databinding.ActivityMainBinding
import com.rafaelmartorano.sicredichallenge.databinding.FragmentHomeBinding
import com.rafaelmartorano.sicredichallenge.presentation.adapter.EventAdapter
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModel
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        eventAdapter = (activity as MainActivity).eventAdapter
        eventAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_event", it)
            }
            findNavController().navigate(
                R.id.action_fragmentHome_to_eventDetailedInfoFragment,
                bundle
            )
        }
        initRecyclerView()
        viewEventList()
    }

    private fun viewEventList(){
        viewModel.getEvents()
        viewModel.events.observe(viewLifecycleOwner, {response ->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        eventAdapter.differ.submitList(it)
                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(context, "An error ocurred: $it", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecyclerView(){
        binding.recyclerViewEvents.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }
}