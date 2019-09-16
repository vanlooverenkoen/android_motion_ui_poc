package com.example.motionuipoc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.motionuipoc.R
import com.example.motionuipoc.adapter.HomeAdapter
import com.example.motionuipoc.databinding.ActivityMainBinding
import com.example.motionuipoc.repository.CarRepository
import com.example.motionuipoc.util.HeroHelper
import com.example.motionuipoc.util.listeners.OnSnapListener
import com.example.motionuipoc.util.listeners.OnSnapPositionChangeListener
import com.icapps.architecture.utils.ext.bindContentView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), OnSnapPositionChangeListener {

    private lateinit var binding: ActivityMainBinding
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = R.layout.activity_main.bindContentView(this)

        binding.carTransitionName = HeroHelper.getCarHero(currentPosition)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = HomeAdapter(CarRepository.getCars()) { position ->
            onItemClicked(position)
        }
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.addOnScrollListener(OnSnapListener(this, snapHelper))
    }

    private fun onItemClicked(position: Int) {
        val backgroundPair = Pair<View, String>(binding.background, "background")
        val headerPair = Pair<View, String>(binding.headerInclude.headerLayout, "header")
        val imagePair = Pair<View, String>(binding.car, HeroHelper.getCarHero(position))
        val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            backgroundPair,
            headerPair,
            imagePair
        )
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent, activityOptionsCompat.toBundle())
        overridePendingTransition(0, 0)
    }

    override fun onSnapPositionChange(position: Int) {
        currentPosition = position
        binding.carTransitionName = HeroHelper.getCarHero(position)
        Picasso.get()
            .load(CarRepository.getCars()[position].carImage)
            .fit()
            .centerInside()
            .into(binding.car)
    }

}
