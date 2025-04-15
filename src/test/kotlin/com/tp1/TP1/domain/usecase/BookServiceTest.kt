package com.tp1.TP1.domain.usecase

import com.tp1.TP1.domain.model.Book
import com.tp1.TP1.domain.port.BookRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BookServiceTest {

    private val repository = mockk<BookRepository>(relaxed = true)
    private val service = BookService(repository)

    @Test
    fun `should add a book with valid title and author`() {
        service.addBook("1984", "George Orwell")
        verify { repository.save(Book("1984", "George Orwell")) }
    }

    @Test
    fun `should throw an error when adding a book with empty title`() {
        val exception = assertThrows<IllegalArgumentException> {
            service.addBook("", "George Orwell")
        }
        assertEquals("Title cannot be empty", exception.message)
    }

    @Test
    fun `should throw an error when adding a book with empty author`() {
        val exception = assertThrows<IllegalArgumentException> {
            service.addBook("1984", "")
        }
        assertEquals("Author cannot be empty", exception.message)
    }

    @Test
    fun `should return all books sorted by title`() {
        val books = listOf(
            Book("Brave New World", "Aldous Huxley"),
            Book("1984", "George Orwell"),
            Book("Animal Farm", "George Orwell")
        )
        every { repository.findAll() } returns books

        val result = service.getAllBooks()

        assertEquals(
            listOf(
                Book("1984", "George Orwell"),
                Book("Animal Farm", "George Orwell"),
                Book("Brave New World", "Aldous Huxley")
            ),
            result
        )
    }
}