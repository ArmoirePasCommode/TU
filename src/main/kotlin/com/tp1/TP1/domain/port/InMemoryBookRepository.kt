package com.tp1.TP1.domain.port

import com.tp1.TP1.domain.model.Book

class InMemoryBookRepository : BookRepository {
    private val books = mutableListOf<Book>()

    override fun save(book: Book) {
        books.add(book)
    }

    override fun findAll(): List<Book> {
        return books.toList()
    }
}