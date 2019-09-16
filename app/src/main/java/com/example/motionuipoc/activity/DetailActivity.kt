package com.example.motionuipoc.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.motionuipoc.R
import com.example.motionuipoc.adapter.DetailAdapter
import com.example.motionuipoc.databinding.ActivityDetailBinding
import com.example.motionuipoc.repository.CarRepository
import com.example.motionuipoc.util.listeners.OnSnapListener
import com.example.motionuipoc.util.listeners.OnSnapPositionChangeListener
import com.icapps.architecture.utils.ext.bindContentView

class DetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = R.layout.activity_detail.bindContentView(this)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = DetailAdapter(CarRepository.getCars())
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, 0)
    }
}
