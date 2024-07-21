package com.example.tweetsy.viewModels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tweetsy.repositories.CategoriesRepository
import com.example.tweetsy.utils.AppUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(categoriesRepository: CategoriesRepository) : ViewModel(){
    private val _categories = MutableStateFlow(emptyList<String>())
    val categories = _categories.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init{
        viewModelScope.launch {
            try {
                val res = categoriesRepository.getCategories()
                if (res.isSuccessful){
                    res.body()?.let {
                        _categories.value = it.record
                    }
                }
                _isLoading.value = false
            }catch (e: Exception){
                Log.e("fetch categories ->", e.message.toString())
                _isLoading.value = false
            }
        }
    }
}