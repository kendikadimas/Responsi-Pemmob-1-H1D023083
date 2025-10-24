package com.unsoed.responsi1h1d023083.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsoed.responsi1h1d023083.data.model.SearchResponse
import com.unsoed.responsi1h1d023083.network.ApiClient
import com.unsoed.responsi1h1d023083.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _teamData = MutableLiveData<SearchResponse?>()
    val teamData: LiveData<SearchResponse?> = _teamData

    fun fetchTeamData(teamId: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getTeamDetails(teamId, Constants.API_TOKEN)
                if(response.isSuccessful){
                    val result = response.body()
                    _teamData.value = result
                    Log.d("SUCCESS_GET_DATA", "$result")
                } else {
                    Log.e("API_ERROR", "${response.code()} ${response.message()}")
                }
            } catch (e: Exception){
                Log.e("API_EXCEPTION", e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}