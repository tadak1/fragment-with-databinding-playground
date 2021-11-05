package com.example.fragmentwithdatabindingplayground.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentwithdatabindingplayground.di.GithubRepository
import com.example.fragmentwithdatabindingplayground.di.GithubUserDetail
import com.example.fragmentwithdatabindingplayground.di.Resource
import com.example.fragmentwithdatabindingplayground.di.Resource.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    val userDetail = MutableLiveData<Resource<GithubUserDetail>>(Loading())

    init {
        viewModelScope.launch {
            userDetail.postValue(repository.getUserById("tadak1"))
        }
    }
}
