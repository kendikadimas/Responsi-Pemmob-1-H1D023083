package com.unsoed.responsi1h1d023083

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unsoed.responsi1h1d023083.HistoryActivity
import com.unsoed.responsi1h1d023083.R
import com.unsoed.responsi1h1d023083.databinding.ActivityMainBinding
import com.unsoed.responsi1h1d023083.viewmodel.MainViewModel
import androidx.activity.viewModels
import com.unsoed.responsi1h1d023083.data.model.CoachData
import com.unsoed.responsi1h1d023083.data.model.SearchResponse
import com.unsoed.responsi1h1d023083.CoachActivity
import com.unsoed.responsi1h1d023083.data.model.PlayerData
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var dataPelatih: CoachData? = null
    private var dataPlayer: List<PlayerData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchTeamData("100")
        setupObservers()
        setupClickListener()
        initLayout()
    }

    private fun setupObservers(){
        viewModel.teamData.observe(this) { SearchResponse->
            if(SearchResponse != null){
                dataPelatih = SearchResponse.coach
                dataPlayer = SearchResponse.team
            }
        }
    }

    private fun setupClickListener(){
        binding.layoutCoach.root.setOnClickListener {
            if (dataPelatih != null) {
                val intent = Intent(this, CoachActivity::class.java)
                intent.putExtra("coach", dataPelatih)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data tidak tersedia", Toast.LENGTH_SHORT).show()
            }
        }

        binding.layoutTeam.root.setOnClickListener {
            if (dataPlayer != null) {
                val intent = Intent(this, PlayerActivity::class.java)
                intent.putExtra("team", dataPlayer as Serializable)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data skuad belum siap", Toast.LENGTH_SHORT).show()
            }
        }

        binding.layoutHistory.root.setOnClickListener {
            val Intent = Intent(this, HistoryActivity::class.java)
            startActivity(Intent)
        }
    }

    private fun initLayout() {
        binding.layoutHistory.let {
            it.imgIcon.setImageResource(R.drawable.ic_ball)
            it.tvLayout.setText(R.string.club)
        }

        binding.layoutCoach.let {
            it.imgIcon.setImageResource(R.drawable.ic_coach)
            it.tvLayout.setText(R.string.coach)
        }

        binding.layoutTeam.let{
            it.imgIcon.setImageResource(R.drawable.ic_team)
            it.tvLayout.setText(R.string.team)
        }

    }

    private fun initListener(){



//        binding.layoutCoach.root.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
////            intent.data = getString(R.string.ig_himpunan).toUri()
//            startActivity(intent)
//        }

//        binding.layoutTeam.root.setOnClickListener {
////            val intent = Intent(Intent.ACTION_SENDTO).apply {
////                data = "mailto:${getString(R.string.email)}".toUri()
////            }
//            startActivity(intent)
//        }

//        binding.layoutPhone.root.setOnClickListener {
//            val intent = Intent(Intent.ACTION_DIAL).apply {
//                data = "tel:${getString(R.string.telepon)}".toUri()
//
//            }
//            startActivity(intent)
//        }

//        binding.layoutBuku.root.setOnClickListener {
//            val intent = Intent(this, DaftarBukuActivity::class.java)
//            startActivity(intent)
//        }

//        binding.btnBack.setOnClickListener {
//            finish()
//        }
    }
}