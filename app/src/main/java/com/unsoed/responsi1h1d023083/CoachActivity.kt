package com.unsoed.responsi1h1d023083

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unsoed.responsi1h1d023083.data.model.CoachData
import com.unsoed.responsi1h1d023083.data.model.SearchResponse
import com.unsoed.responsi1h1d023083.databinding.ActivityCoachBinding

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val coach = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("coach", CoachData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("coach") as? CoachData
        }

        if (coach != null) {
            binding.textCoachName.text = coach.name ?: "N/A"
            binding.textCoachDob.text = coach.dateOfBirth ?: "N/A"
            binding.textCoachNationality.text = coach.nationality ?: "N/A"
        }
    }
}