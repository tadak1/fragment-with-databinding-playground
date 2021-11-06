package com.example.fragmentwithdatabindingplayground.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentwithdatabindingplayground.model.GithubUserDetail
import com.example.fragmentwithdatabindingplayground.model.Resource
import com.example.fragmentwithdatabindingplayground.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    val userDetail = MutableLiveData<Resource<GithubUserDetail>>(Resource.Loading())

    init {
        viewModelScope.launch {
            userDetail.postValue(repository.getUserById("tadak1"))
        }
    }
}
