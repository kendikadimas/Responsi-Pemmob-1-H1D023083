package com.unsoed.responsi1h1d023083

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.unsoed.responsi1h1d023083.data.model.PlayerData
import com.unsoed.responsi1h1d023083.databinding.FragmentPlayerDetailBinding

class PlayerDetailFragment : Fragment() {

    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!
    private var player: PlayerData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializable("PLAYER_DETAIL_KEY", PlayerData::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getSerializable("PLAYER_DETAIL_KEY") as? PlayerData
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        player?.let {
            binding.textDetailName.text = it.name ?: "N/A"
            binding.textDetailPosition.text = it.position ?: "N/A"
            binding.textDetailDob.text = it.dateOfBirth ?: "N/A"
            binding.textDetailNationality.text = it.nationality ?: "N/A"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(player: PlayerData) =
            PlayerDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("PLAYER_DETAIL_KEY", player)
                }
            }
    }
}