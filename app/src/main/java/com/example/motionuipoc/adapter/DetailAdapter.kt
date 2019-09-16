package com.example.motionuipoc.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionuipoc.R
import com.example.motionuipoc.databinding.ViewCarDetailBinding
import com.example.motionuipoc.model.Car
import com.example.motionuipoc.repository.CarRepository
import com.example.motionuipoc.util.HeroHelper
import com.icapps.architecture.utils.ext.inflate

class DetailAdapter(private val cars: List<Car>) :
    RecyclerView.Adapter<DetailAdapter.CarVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarVH =
        CarVH(R.layout.view_car_detail.inflate(parent))

    override fun getItemCount(): Int = CarRepository.getCars().size

    override fun onBindViewHolder(holder: CarVH, position: Int) {
        holder.bind(position)
    }

    inner class CarVH(private val binding: ViewCarDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val car = cars[position]
            binding.model = car.model
            binding.imageRes = car.manufacturerLogo
            binding.carTransitionName = HeroHelper.getCarHero(position)
            binding.cardTransitionName = HeroHelper.getCardHero(position)
            binding.executePendingBindings()
        }
    }
}