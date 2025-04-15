package com.tp1.TP1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Tp1Application {
    fun main(args: Array<String>) {
        runApplication<Tp1Application>(*args)
    }
    fun cipher(char: Char, shift: Int): Char {
        require(shift >= 0) { "Shift value cannot be negative" }
        val alphabet = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
        return if (char in alphabet) {
            val index = alphabet.indexOf(char)
            val newIndex = (index + shift) % alphabet.size
            alphabet[newIndex]
        } else {
            char
        }
    }
}
