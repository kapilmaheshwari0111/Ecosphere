package com.example.mobileappsdg

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout

class ResourceCalculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resource_calculator)

        // Get references to UI elements
        val tilMilesDriven = findViewById<TextInputLayout>(R.id.tilMilesDriven)
        val tilFlightsPerYear = findViewById<TextInputLayout>(R.id.tilFlightsPerYear)
        val tilElectricityUsage = findViewById<TextInputLayout>(R.id.tilElectricityUsage)
        val tilMeatConsumption = findViewById<TextInputLayout>(R.id.tilMeatConsumption)
        val tilClothesSpending = findViewById<TextInputLayout>(R.id.tilClothesSpending)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvFeedback = findViewById<TextView>(R.id.tvFeedback)

        btnCalculate.setOnClickListener {
            // Get values from input fields
            val milesDriven = tilMilesDriven.editText?.text.toString().toDoubleOrNull() ?: 0.0
            val flightsPerYear = tilFlightsPerYear.editText?.text.toString().toIntOrNull() ?: 0
            val electricityUsage = tilElectricityUsage.editText?.text.toString().toDoubleOrNull() ?: 0.0
            val meatConsumption = tilMeatConsumption.editText?.text.toString().toIntOrNull() ?:0
            val clothesSpending = tilClothesSpending.editText?.text.toString().toDoubleOrNull() ?: 0.0
            val resultContainer = findViewById<MaterialCardView>(R.id.resultContainer)

            // Calculate carbon footprint
            val footprint = calculateCarbonFootprint(
                milesDriven,
                flightsPerYear,
                electricityUsage,
                meatConsumption,
                clothesSpending
            )

            // Display results
            resultContainer.visibility = View.VISIBLE
            tvResult.visibility = View.VISIBLE
            tvFeedback.visibility = View.VISIBLE
            tvResult.text = "Your estimated carbon footprint is: ${String.format("%.2f", footprint)} tonnes CO2e"

            // Provide feedback and tips
            val feedback = when {
                footprint < 5 -> "Excellent! You have a very low carbon footprint. Keep up the great work!"
                footprint in 5.0..10.0 -> "Good job! You're doing better than average. Consider making some small changes to reduce your impact further."
                footprint in 10.0..15.0 -> "Your carbon footprint is around average. There's room for improvement. Explore ways to reduce your emissions."
                else -> "Your carbon footprint is quite high. It's time to make significant changes to your lifestyle to reduce your impact on the environment."
            }
            tvFeedback.text = feedback

            // Apply window insets to the ScrollView
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.scrollView)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    // Function to calculate carbon footprint based on user inputs
    private fun calculateCarbonFootprint(
        milesDriven: Double,
        flightsPerYear: Int,
        electricityUsage: Double,
        meatConsumption: Int,
        clothesSpending: Double
    ): Double {
        var footprint = 0.0

        // Transportation
        footprint += milesDriven * 0.404 // Average emissions per mile
        footprint += flightsPerYear * 0.254 // Average emissions per flight

        // Energy
        footprint += electricityUsage * 0.00053 // Average emissionsper kWh

        // Food
        footprint += meatConsumption * 0.025 // Average emissions per meat serving

        // Shopping
        footprint += clothesSpending * 0.005 // Average emissions per dollar spent on clothes

        return footprint
    }
}