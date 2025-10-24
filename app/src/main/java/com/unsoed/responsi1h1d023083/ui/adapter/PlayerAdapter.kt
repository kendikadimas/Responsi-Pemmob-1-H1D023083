package com.unsoed.responsi1h1d023083

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1h1d023083.data.model.PlayerData
import com.unsoed.responsi1h1d023083.databinding.ListPlayerBinding

interface OnPlayerClickListener {
    fun onPlayerClick(player: PlayerData)
}

class PlayerAdapter(
    private val context: Context,
    private val playerList: List<PlayerData>,
    private val listener: OnPlayerClickListener
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(val binding: ListPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ListPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]

        holder.binding.tvPlayerName.text = player.name ?: "No Name"
        holder.binding.tvPosition.text = player.position ?: "No Position"

        val colorRes = getPositionColor(player.position)
        holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, colorRes))

        holder.binding.root.setOnClickListener {
            listener.onPlayerClick(player)
        }
    }

    private fun getPositionColor(position: String?): Int {
        return when (position) {
            "Goalkeeper" -> R.color.yellow
            "Defence", "Centre-Back", "Right-Back", "Left-Back" -> R.color.blue
            "Midfield", "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> R.color.green
            "Offence", "Centre-Forward", "Left Winger", "Right Winger" -> R.color.red
            else -> R.color.grey
        }
    }
}