package com.lucaspowered.hwm.test

import com.lucaspowered.hwm.database.*
import kotlin.test.*

class AccountTests {
    // Test user account
    private val account = Account("000")

    @Test
    fun `test account name`(){
        assertEquals("Firstname Lastname", account.fullName)
    }

}