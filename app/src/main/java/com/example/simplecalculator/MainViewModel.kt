package com.example.simplecalculator

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _input1 = mutableStateOf("")
    val input1: State<String> = _input1
    fun setInput1(value: String) {
        _input1.value = value
    }

    private val _input2 = mutableStateOf("0")
    val input2: State<String> = _input2
    fun setInput2(value: String) {
        _input2.value = value
    }

    private val _sum = mutableStateOf("0")
    val sum: State<String> = _sum
    fun setSum(value: String) {
        _sum.value = value
    }

    fun calculateSum(num1: Int, num2: Int) {
        val sum = num1 + num2

        setSum(sum.toString())
    }
}
