package com.github.aicoleman.digimon_flow_sample.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.aicoleman.digimon_flow_sample.data.model.Digimon
import com.github.aicoleman.digimon_flow_sample.data.model.State
import com.github.aicoleman.digimon_flow_sample.repository.DigimonRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DigimonViewModel @ViewModelInject constructor(
    val digimonRepository: DigimonRepository
) : ViewModel() {

    private val _digimonListLiveData = MutableLiveData<State<List<Digimon>>>()

    val digimonListLiveData: LiveData<State<List<Digimon>>>
        get() = _digimonListLiveData

    fun getDigimonList() {
        viewModelScope.launch {
            digimonRepository.getDigimonList().collect {
                _digimonListLiveData.value = it
            }
        }
    }

    private val _digimonInfoLiveData = MutableLiveData<State<List<Digimon>>>()

    val digimonInfoLiveData: LiveData<State<List<Digimon>>>
        get() = _digimonInfoLiveData

    fun getDigimonInfo(name: String) {
        viewModelScope.launch {
            digimonRepository.getDigimonInfo(name).collect {
                _digimonInfoLiveData.value = it
            }
        }
    }
}