package com.example.personacreator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonaEditViewModel : ViewModel() {
    //地理的変数のText保存リストの生成？
    private val _geographyInputs = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    val geographyInputs: LiveData<MutableList<String>> = _geographyInputs
    //行動変数保存リストの生成？
    private val _actionInputs = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    val actionInputs: LiveData<MutableList<String>> = _actionInputs
    //人口動態変数保存リスト？
    private val _populationInputs = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    val populationInputs: LiveData<MutableList<String>> = _populationInputs
    //心理的変数保存リスト？
    private val _psycologyInputs = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }
    val psycologyInputs: LiveData<MutableList<String>> = _psycologyInputs

    //-------------------------------------------------------------変数への追加メソッド集合群↓↓↓
    //地理的変数への追加メソッド
    fun addGeographyInput(input: String) {
        _geographyInputs.value?.add(input)
        _geographyInputs.value = _geographyInputs.value // Trigger observers
    }
    //行動変数への追加メソッド
    fun addActionInput(input: String) {
        _actionInputs.value?.add(input)
        _actionInputs.value = _actionInputs.value // Trigger observers
    }
    //人口動態変数への追加メソッド
    fun addpopulationInput(input: String) {
        _populationInputs.value?.add(input)
        _populationInputs.value = _populationInputs.value // Trigger observers
    }
    //心理的変数への追加メソッド
    fun addpsychologyInput(input: String) {
        _psycologyInputs.value?.add(input)
        _psycologyInputs.value = _psycologyInputs.value // Trigger observers
    }

    //-------------------------------------------------------------------変数への更新実行メソッド群↓↓↓
    //地理的変数
    fun updateGeographyInput(index: Int, input: String) {
        _geographyInputs.value?.set(index, input)
        _geographyInputs.value = _geographyInputs.value // Trigger observers
    }
    //行動変数
    fun updateActionInput(index: Int, input: String) {
        _actionInputs.value?.set(index, input)
        _actionInputs.value = _actionInputs.value // Trigger observers
    }
    //人口動態変数
    fun updatePopulationInput(index: Int, input: String) {
        _populationInputs.value?.set(index, input)
        _populationInputs.value = _populationInputs.value // Trigger observers
    }
    //心理的変数
    fun updatePsychologyInput(index: Int, input: String) {
        _psycologyInputs.value?.set(index, input)
        _psycologyInputs.value = _psycologyInputs.value // Trigger observers
    }
}
