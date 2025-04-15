import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.string
import io.kotest.property.checkAll
import io.kotest.property.arbitrary.filter
import com.tp1.TP1.domain.model.Book
import com.tp1.TP1.domain.port.InMemoryBookRepository
import com.tp1.TP1.domain.usecase.BookService

class BookServicePropertyTest : StringSpec({
    "getAllBooks should return all stored books" {
        // Define arbitraries for non-blank strings
        val nonBlankStringArb = Arb.string(minSize = 1).filter { it.isNotBlank() }

        checkAll(
            Arb.list(nonBlankStringArb, 1..10),
            Arb.list(nonBlankStringArb, 1..10)
        ) { titles, authors ->
            val books = titles.zip(authors).map { (title, author) ->
               Book(title, author)
            }
            val repository = InMemoryBookRepository()
            books.forEach { repository.save(it) }
            val service = BookService(repository)
            val result = service.getAllBooks()

            assert(result.size == books.size)
            assert(result.containsAll(books))
        }
    }
})