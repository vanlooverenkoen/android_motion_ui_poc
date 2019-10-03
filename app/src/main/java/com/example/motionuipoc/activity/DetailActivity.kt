package com.example.motionuipoc.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
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

class DetailActivity : AppCompatActivity(), OnSnapPositionChangeListener {

    companion object {
        const val EXTRA_SELECTED_CAR_ID = "carId"

        fun create(context: Context, selectedCarId: String): Intent {
            return Intent(context, DetailActivity::class.java).putExtra(
                EXTRA_SELECTED_CAR_ID,
                selectedCarId
            )
        }
    }

    private lateinit var binding: ActivityDetailBinding

    private val selectedCarId: String by lazy { intent.getStringExtra(EXTRA_SELECTED_CAR_ID) }
    lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        binding = R.layout.activity_detail.bindContentView(this)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = DetailAdapter(CarRepository.getCars()) {
            val position = CarRepository.getCars().indexOfFirst { it.id == selectedCarId }
            binding.recyclerView.scrollToPosition(position)
            binding.recyclerView.post {
                startPostponedEnterTransition()
                showDetailedInfo();
            }
        }
        binding.recyclerView.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.addOnScrollListener(OnSnapListener(this, snapHelper))
    }

    private fun showDetailedInfo() {
        Handler().postDelayed({
            adapter.setVisibleMoreButton()
        }, 400)
    }

    override fun onSnapPositionChange(position: Int) {
        val cars = CarRepository.getCars()
        val resultIntent =
            cars.getOrNull(position)?.let { Intent().putExtra(EXTRA_SELECTED_CAR_ID, it.id) }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    override fun onBackPressed() {
        adapter.hideMoreButton()
        Handler().post {
            super.onBackPressed()
            overridePendingTransition(0, 0)
        }
    }
}
