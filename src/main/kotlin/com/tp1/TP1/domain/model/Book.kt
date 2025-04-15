package com.tp1.TP1.domain.model

data class Book(val title: String, val author: String) {
    init {
        require(title.isNotBlank()) { "Title cannot be empty" }
        require(author.isNotBlank()) { "Author cannot be empty" }
    }
}