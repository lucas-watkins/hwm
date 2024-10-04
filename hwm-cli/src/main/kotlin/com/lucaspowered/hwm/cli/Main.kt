package com.lucaspowered.hwm.cli

import com.lucaspowered.hwm.core.*
import com.lucaspowered.hwm.database.*
import com.lucaspowered.hwm.homework.Homework
import com.lucaspowered.hwm.homework.Subject
import java.time.LocalDateTime
import java.time.Year

private lateinit var acc: Account

fun input(prompt: String) = readln(prompt).trim().lowercase()

private fun String.either(vararg s: String) : Boolean {
    return s.any {i -> this == i}
}

private fun readln(prompt: String): String {
    print(prompt)
    return readln()
}
private fun readInt(prompt: String): Int {
    print(prompt)
    while (true) {
        try {
            return Integer.parseInt(readln())
        } catch (e: NumberFormatException) {
            print(prompt)
        }
    }
}

private fun readSubject(prompt: String): String {
    while (true){
        val subject = readln(prompt)
        if (acc.getHomework().any {it.name == subject}) return subject
        println("Invalid Subject!")
    }
}

fun main() {
    try {
        acc = Account(Parameters.config.userToken)
    } catch (e: InvalidAccountException) {
        println("Invalid account token in configuration file.")
        return
    }

    while (true){
        val command = input(">>> ")
        when (command) {
            "add" -> add()
            "show" -> show()
            else -> println("Invalid command. Valid commands are add, show")
        }
    }
}

fun show(){
    for (hw in acc.getHomework()){
        println("Class: ${hw.name}")
        hw.assignments.forEach {
            println("\t- ${it.assignmentDetails}")
            println("\t  Due: ${it.dueDate}")
        }
    }
}

fun add(){
    val inp = input("Homework (hw) or subject (sbj): ")
    if (inp.either("subject", "sbj")){
        acc.append(Subject(readln("Subject Name: ")))
    }

    if (inp.either("homework", "hw")){
        acc.append(
            Homework(
                readln("Assignment Title:\n"),
                LocalDateTime.of(
                    Year.now().value, readInt("Month Due: "), readInt("Day Due: "),
                    0, 0)
            ),
            readSubject("Subject: ")
        )
    }

}