package com.dioses.weather.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.dioses.weather.BR
import com.dioses.weather.R
import com.dioses.weather.databinding.ActivityMainBinding
import com.dioses.weather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
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
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch { //6a5c325c9265883997730d09be2328e8
            binding.viewModel?.getWeatherAndForecast(
                -4.907692770341739,
                -80.71946571717677,
                "6a5c325c9265883997730d09be2328e8",
                "metric",
                "en"
            )
        }
    }

}