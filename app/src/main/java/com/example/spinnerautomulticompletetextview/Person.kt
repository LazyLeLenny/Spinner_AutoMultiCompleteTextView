package com.example.spinnerautomulticompletetextview

data class Person(val name: String, val lastName: String, val age: Int, val position: String) {
    override fun toString(): String {
        return "$name $lastName \n Возраст: $age \n Должность: $position"
    }
}