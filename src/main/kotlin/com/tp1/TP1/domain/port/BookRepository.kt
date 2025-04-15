package com.tp1.TP1.domain.port

import com.tp1.TP1.domain.model.Book

interface BookRepository {
    fun save(book: Book)
    fun findAll(): List<Book>
}