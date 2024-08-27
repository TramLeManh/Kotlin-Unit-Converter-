package com.example.kotlin_unit_converter

enum class converterFunction {
    km,
    m,
    cm,
    mm;

    companion object {
        private fun conversionToMeters(unit: converterFunction): Double {
            return when (unit) {
                km -> 1000.0
                m -> 1.0
                cm -> 0.01
                mm -> 0.001
            }
        }

        fun convert(value: Double, from: converterFunction, to: converterFunction): Double {

            val valueInMeters = value * conversionToMeters(from)
            val factor = 1 / conversionToMeters(to)
            return valueInMeters * factor
        }
    }


}