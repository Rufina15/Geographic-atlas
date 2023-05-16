package com.example.geographicatlas.presentation.country_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.common.Constants
import com.example.geographicatlas.common.Resource
import com.example.geographicatlas.domain.use_case.get_country.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CountryDetailsState())
    val state: State<CountryDetailsState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COUNTRY_CCA2)?.let { cca2 ->
            getCountry(cca2)
        }
    }

    private fun getCountry(cca2: String) {
        getCountryUseCase(cca2).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CountryDetailsState(country = result.data)
                }

                is Resource.Loading -> {
                    _state.value = CountryDetailsState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value = CountryDetailsState(
                        error = result.message ?: "Error while getting countries list"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}