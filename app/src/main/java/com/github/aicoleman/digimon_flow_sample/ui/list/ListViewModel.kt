package com.github.aicoleman.digimon_flow_sample.ui.list

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

class ListViewModel @ViewModelInject constructor(
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
}