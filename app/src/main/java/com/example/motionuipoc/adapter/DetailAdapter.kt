package com.example.motionuipoc.adapter

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionuipoc.R
import com.example.motionuipoc.databinding.ViewCarDetailBinding
import com.example.motionuipoc.model.Car
import com.example.motionuipoc.repository.CarRepository
import com.icapps.architecture.utils.ext.inflate

class DetailAdapter(private val cars: List<Car>, private val initialBindComplete: () -> Unit) :
    RecyclerView.Adapter<DetailAdapter.CarVH>() {

    private companion object {
        const val DEBOUNCE_DELAY = 25L
    }

    private val handler = Handler()
    private var hasCompletedInitialBind: Boolean = false
    private var recycler: RecyclerView? = null
    private var moreVisibility = View.INVISIBLE

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recycler = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.recycler = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarVH =
        CarVH(R.layout.view_car_detail.inflate(parent))

    override fun getItemCount(): Int = CarRepository.getCars().size

    override fun onBindViewHolder(holder: CarVH, position: Int) {
        holder.bind(position)
        if (!hasCompletedInitialBind) {
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed(::confirmInitialBind, DEBOUNCE_DELAY)
        }
    }

    private fun confirmInitialBind() {
        handler.removeCallbacksAndMessages(null)
        if (!hasCompletedInitialBind) {
            hasCompletedInitialBind = true
            recycler?.post { initialBindComplete() }
        }
    }

    fun setVisibleMoreButton() {
        moreVisibility = View.VISIBLE
        notifyDataSetChanged()
    }

    fun hideMoreButton() {
        moreVisibility = View.INVISIBLE
        notifyDataSetChanged()
    }

    inner class CarVH(private val binding: ViewCarDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val car = cars[position]
            binding.car = car
            binding.moreInfo.visibility = moreVisibility
            binding.modelInfo.visibility = moreVisibility
            binding.executePendingBindings()
        }
    }
}