package com.example.tweetsy.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.tweetsy.Tweets
import com.example.tweetsy.models.Tweet
import com.example.tweetsy.repositories.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetsViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val _tweets = MutableStateFlow(emptyList<Tweet>())
    val tweets = _tweets.asStateFlow()

    val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _category = savedStateHandle.toRoute<Tweets>().category

    init{
        viewModelScope.launch {
            try{
                val res = tweetRepository.getTweetsByCategory(_category)
                if(res.isSuccessful){
                    res.body()?.let {
                        _tweets.value = it.record
                    }
                    _isLoading.value = false
                }
            }catch (e: Exception){
                Log.e("fetch tweets ->", e.message.toString())
                _isLoading.value = false
            }
        }
    }
}