package com.unsoed.responsi1h1d023083

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1h1d023083.data.model.PlayerData
import com.unsoed.responsi1h1d023083.databinding.ActivityPlayerBinding // Dibuat dari activity_player.xml
import java.io.Serializable

class PlayerActivity : AppCompatActivity(), OnPlayerClickListener {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val squad = getSquadListFromIntent()

        if (!squad.isNullOrEmpty()) {
            setupRecyclerView(squad)
        }
    }

    private fun getSquadListFromIntent(): List<PlayerData>? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("team",Serializable::class.java) as? List<PlayerData>
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("team") as? List<PlayerData>
        }
    }

    private fun setupRecyclerView(squad: List<PlayerData>) {

        playerAdapter = PlayerAdapter(this, squad, this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlayerActivity)
            adapter = playerAdapter
        }
    }

    override fun onPlayerClick(player: PlayerData) {

        val fragment = PlayerDetailFragment.newInstance(player)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}