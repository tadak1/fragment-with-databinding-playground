package com.example.fragmentwithdatabindingplayground.ui.detail

import androidx.lifecycle.*
import com.example.fragmentwithdatabindingplayground.model.GithubUserDetail
import com.example.fragmentwithdatabindingplayground.model.Resource
import com.example.fragmentwithdatabindingplayground.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailUiState(
    val isLoading: Boolean = false,
    val userDetail: GithubUserDetail? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private val _userDetail = MutableLiveData<Resource<GithubUserDetail>>(Resource.Loading())
    val userDetail: LiveData<DetailUiState> = Transformations.map(_userDetail) {
        print(it)
        when (it) {
            is Resource.Success -> DetailUiState(userDetail = it.data)
            is Resource.Loading -> DetailUiState(isLoading = true)
            is Resource.Error -> DetailUiState(errorMessage = it.message)
        }
    }
    init {
        loadUserDetail()
    }

    fun loadUserDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getUserById("tadak1")
            _userDetail.postValue(result)
        }
    }
}
