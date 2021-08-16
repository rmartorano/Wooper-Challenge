package com.rafaelmartorano.sicredichallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.newsapiclient.data.util.Resource
import com.rafaelmartorano.sicredichallenge.R
import com.rafaelmartorano.sicredichallenge.databinding.ActivityMainBinding
import com.rafaelmartorano.sicredichallenge.presentation.adapter.EventAdapter
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModel
import com.rafaelmartorano.sicredichallenge.presentation.viewmodel.EventViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var factory: EventViewModelFactory
    @Inject lateinit var eventAdapter: EventAdapter
    lateinit var viewModel: EventViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory)[EventViewModel::class.java ]
    }
}