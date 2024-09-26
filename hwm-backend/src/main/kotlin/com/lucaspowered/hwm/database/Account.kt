package com.lucaspowered.hwm.database

import com.lucaspowered.hwm.core.InvalidAccountException
import org.ktorm.schema.*

data class Account(val token: String){

    init {
        if (!isValid) {
            throw InvalidAccountException("Invalid account token")
        }
    }

    val firstName : String by lazy  {
        getValueAsString(Accounts.firstNames)
    }

    val lastName : String by lazy  {
        getValueAsString(Accounts.lastNames)
    }

    val fullName = "$firstName $lastName"

    val isValid : Boolean
        get() = getValueAsString(Accounts.tokens).isNotEmpty()
}

object Accounts : Table<Nothing>("t_accounts") {
    val tokens = varchar("token").primaryKey()
    val firstNames = varchar("first_name")
    val lastNames = varchar("last_name")
    val homework = text("homework")
}