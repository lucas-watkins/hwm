package com.lucaspowered.hwm.database

import com.lucaspowered.hwm.core.Parameters
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.schema.Column
import com.lucaspowered.hwm.homework.*
import kotlinx.serialization.json.*
import kotlinx.serialization.*

private val database = Database.connect(url = Parameters.config.dbUrl,
                        driver = Parameters.config.dbDriver,
                        password = Parameters.config.dbPassword,
                        user = Parameters.config.dbUsername)

private fun getUserAsQuery(token: String): Query {
    return database.from(Accounts)
        .select()
        .where (Accounts.tokens eq token)
}

// Should be able to hand Column<Int> and Column<String> with multiple columns
fun <T : Any> Account.getValueAsString(vararg columns: Column<T>): String {
    val result = StringBuilder()
    val q = getUserAsQuery(token)
    columns.forEach { column ->
        q.forEach { row -> result.append(row[column]) }
    }
    return result.toString()
}

private fun <T : Any> updateUser(token: String, value: T, attribute: Column<T>){
    database.update(Accounts){
        where {
            it.tokens eq token
        }
        set(attribute, value)
    }
}

fun Account.getHomework(): Array<Subject> {
    return Json.decodeFromString<Array<Subject>>(getValueAsString(Accounts.homework))
}

fun Account.append(homework: Homework, subject: String){
    val hw = getHomework()
    val index = hw.indexOf(subject)
    val hwList = hw[index].assignments.toMutableList()
    hwList.add(homework)
    hw[index].assignments = hwList.toTypedArray()
    updateUser(token, Json.encodeToString(hw), Accounts.homework)
}

fun Account.append(subject: Subject){
    val subjList = getHomework().toMutableList()
    subjList.add(subject)
    updateUser(token, Json.encodeToString(subjList.toTypedArray()), Accounts.homework)
}

fun Account.removeSubject(subject: String){
    val subjList = getHomework().toMutableList()
    updateUser(token, Json.encodeToString(subjList.filter {it.name != subject}.toTypedArray()),
        Accounts.homework)
}

fun Array<Subject>.indexOf(subject: String): Int {
    return indexOfFirst {it.name == subject}
}

fun Account.removeHomework(homeworkNumber: Int, subject: String){
    val hw = getHomework()
    val index  = hw.indexOf(subject)
    val hwlist = hw[index].assignments.toMutableList()
    hwlist.removeAt(homeworkNumber)
    hw[index].assignments = hwlist.toTypedArray()
    updateUser(token, Json.encodeToString(hw), Accounts.homework)
}
