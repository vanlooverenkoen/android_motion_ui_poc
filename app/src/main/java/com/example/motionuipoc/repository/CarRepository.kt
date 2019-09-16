package com.example.motionuipoc.repository

import com.example.motionuipoc.R
import com.example.motionuipoc.model.Car

class CarRepository {

    companion object {
        fun getCars(): List<Car> {
            val list = mutableListOf<Car>()
            list.add(
                Car(
                    "i8",
                    "BMW",
                    "Sporty",
                    R.drawable.bmw,
                    R.drawable.bmw_logo,
                    "fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew f"
                )
            )
            list.add(
                Car(
                    "Model X",
                    "Tesla",
                    "Elon",
                    R.drawable.tesla,
                    R.drawable.tesla_logo,
                    "fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew f"
                )
            )
            list.add(
                Car(
                    "Virage",
                    "Bond, James Bond",
                    "Aston Martin",
                    R.drawable.aston_martin,
                    R.drawable.aston_martin_logo,
                    "fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew fasdfj iweqyfr ewf ioqh rfioqew f"
                )
            )
            return list
        }
    }
}