package com.unsoed.responsi1h1d023083.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName ("coach")
    val coach: CoachData?,
    @SerializedName ("squad")
    val team: List<PlayerData>?
) : Serializable

data class CoachData(
    @SerializedName ("name")
    val name: String?,
    @SerializedName ("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName ("nationality")
    val nationality: String?
) : Serializable

data class PlayerData(
    @SerializedName ("name")
    val name: String?,
    @SerializedName ("position")
    val position: String?,
    @SerializedName ("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName ("nationality")
    val nationality: String?
) : Serializable

