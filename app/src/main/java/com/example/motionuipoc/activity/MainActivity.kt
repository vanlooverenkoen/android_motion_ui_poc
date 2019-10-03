package com.example.motionuipoc.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.motionuipoc.R
import com.example.motionuipoc.adapter.HomeAdapter
import com.example.motionuipoc.databinding.ActivityMainBinding
import com.example.motionuipoc.databinding.ViewCarBinding
import com.example.motionuipoc.model.Car
import com.example.motionuipoc.repository.CarRepository
import com.example.motionuipoc.util.HeroHelper
import com.example.motionuipoc.util.listeners.OnSnapListener
import com.example.motionuipoc.util.listeners.OnSnapPositionChangeListener
import com.icapps.architecture.utils.ext.bindContentView

class MainActivity : AppCompatActivity(), OnSnapPositionChangeListener {

    private companion object {
        private const val STATE_SNAP_POS = "snapPosition"
        private const val REQ_DETAIL = 4641
    }

    private lateinit var binding: ActivityMainBinding
    private var snapPosition: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        binding = R.layout.activity_main.bindContentView(this)
        setupRecyclerView(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_DETAIL) {
            val carId = data?.getStringExtra(DetailActivity.EXTRA_SELECTED_CAR_ID)
            carId?.let {
                val newSnapPosition = CarRepository.getCars().indexOfFirst { it.id == carId }
                if (newSnapPosition >= 0) snapPosition = newSnapPosition
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        snapPosition?.let { outState.putInt(STATE_SNAP_POS, it) }
    }

    private fun setupRecyclerView(savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter =
            HomeAdapter(CarRepository.getCars(), ::onItemClicked, ::onInitialBindComplete)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        binding.recyclerView.addOnScrollListener(OnSnapListener(this, snapHelper))

        val snapPosition = snapPosition
        if (snapPosition == null) {
            this.snapPosition = savedInstanceState?.getInt(STATE_SNAP_POS)
            snapPosition?.let { binding.recyclerView.scrollToPosition(it) }
        } else {
            binding.recyclerView.scrollToPosition(snapPosition)
        }
    }

    private fun onInitialBindComplete() {
        startPostponedEnterTransition()
    }

    private fun onItemClicked(car: Car, itemBinding: ViewCarBinding) {
        val backgroundPair = Pair<View, String>(binding.background, "background")
        val headerPair = Pair<View, String>(binding.headerInclude.headerLayout, "header")

        val carImagePair = Pair<View, String>(binding.carImage, HeroHelper.getCarImageHero(car))
        val cardImagePair = Pair<View, String>(itemBinding.card, HeroHelper.getCardHero(car))
        val logoImagePair = Pair<View, String>(itemBinding.logo, HeroHelper.getLogoImageHero(car))

        val activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            backgroundPair,
            headerPair,
            carImagePair,
            cardImagePair,
            logoImagePair
        )
        val intent = DetailActivity.create(this, car.id)
        startActivityForResult(intent, REQ_DETAIL, activityOptionsCompat.toBundle())
        overridePendingTransition(0, 0)
    }

    override fun onSnapPositionChange(position: Int) {
        val car = CarRepository.getCars()[position]
        binding.car = car
        snapPosition = position
    }

}
