package com.dioses.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dioses.weather.BR
import com.dioses.weather.R
import com.dioses.weather.common.entities.Forecast
import com.dioses.weather.common.utils.CommonUtils
import com.dioses.weather.databinding.ActivityMainBinding
import com.dioses.weather.mainModule.view.adapters.ForecastAdapter
import com.dioses.weather.mainModule.view.adapters.OnClickListener
import com.dioses.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)

    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackBarMsg().observe(this) { resMsg ->
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
            it.getResult().observe(this) { result ->
                adapter.submitList(result.hourly)
            }
        }
    }

    private fun setupAdapter() {
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { //6a5c325c9265883997730d09be2328e8
            binding.viewModel?.getWeatherAndForecast(
                -4.907692770341739,
                -80.71946571717677,
                "6a5c325c9265883997730d09be2328e8",
                "hourly",
                "metric",
                "en"
            )
        }
    }

    /**
     * OnClickListener
     */
    override fun onClick(forecast: Forecast) {
        Snackbar.make(binding.root, CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_LONG)
            .show()
    }

}