package com.lucaspowered.hwm.homework
import kotlinx.serialization.Serializable

@Serializable
data class Subject(val name: String, var assignments: Array<Homework> = arrayOf())