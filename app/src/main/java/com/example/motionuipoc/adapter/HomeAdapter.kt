package com.example.motionuipoc.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.motionuipoc.R
import com.example.motionuipoc.databinding.ViewCarBinding
import com.example.motionuipoc.model.Car
import com.example.motionuipoc.repository.CarRepository
import com.example.motionuipoc.util.HeroHelper
import com.icapps.architecture.utils.ext.inflate

class HomeAdapter(private val cars: List<Car>, private val onClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.CarVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarVH {
        return CarVH(R.layout.view_car.inflate(parent), onClick)
    }

    override fun getItemCount(): Int = CarRepository.getCars().size

    override fun onBindViewHolder(holder: CarVH, position: Int) {
        holder.bind(position)
    }

    inner class CarVH(
        private val binding: ViewCarBinding,
        private val onClick: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val car = cars[position]
            binding.model = car.model
            binding.imageRes = car.manufacturerLogo
            binding.carTransitionName = HeroHelper.getCarHero(position)
            binding.cardTransitionName = HeroHelper.getCardHero(position)
            binding.card.setOnClickListener { onClick(position) }
            binding.executePendingBindings()
        }
    }
}