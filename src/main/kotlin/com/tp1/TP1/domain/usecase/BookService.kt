package com.tp1.TP1.domain.usecase

import com.tp1.TP1.domain.model.Book
import com.tp1.TP1.domain.port.BookRepository

class BookService(private val repository: BookRepository) {
    fun addBook(title: String, author: String) {
        val book = Book(title, author)
        repository.save(book)
    }

    fun getAllBooks(): List<Book> {
        return repository.findAll().sortedBy { it.title }
    }
}