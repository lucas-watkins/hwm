package com.lucaspowered.hwm.test

import kotlin.test.*
import com.lucaspowered.hwm.database.*
import com.lucaspowered.hwm.homework.Homework
import com.lucaspowered.hwm.homework.Subject
import java.time.LocalDateTime


class Homework {

    private val account = Account("000")

    @Test
    fun `all sequentially`(){
        `append subject`()
        `append homework`()
        `remove homework`()
        `remove subject`()
    }

    private fun `append subject`(){
        account.append(Subject("Math"))
        assertEquals("[{\"name\":\"Math\",\"assignments\":[]}]", account.getValueAsString(Accounts.homework))
    }


    private fun `append homework`() {
        account.append(Homework("Read Textbook",
            LocalDateTime.of(2024, 9, 16, 8, 0)), "Math")
        assertEquals("[{\"name\":\"Math\",\"assignments\":" +
                "[{\"assignmentDetails\":\"Read Textbook\",\"dueDate\":\"2024-09-16T08:00\"}]}]",
            account.getValueAsString(Accounts.homework))
    }


    private fun `remove homework`(){
        account.removeHomework(0, "Math")
        assertEquals("[{\"name\":\"Math\",\"assignments\":[]}]", account.getValueAsString(Accounts.homework))
    }


    private fun `remove subject`(){
        account.removeSubject("Math")
        assertEquals("[]", account.getValueAsString(Accounts.homework))
    }
}