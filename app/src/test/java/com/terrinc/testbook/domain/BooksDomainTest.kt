package com.terrinc.testbook.domain

import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.presentation.BookUi
import com.terrinc.testbook.presentation.BooksUi
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.IllegalStateException

/**
 * test for [BooksDomain.Success.map]
 */
class BooksDomainTest {

    @Test
    fun test_map() {
        val mapper = object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                return BookUi.Base(id, name)
            }
        }

        val domain = BooksDomain.Success(listOf(
            BookData(1, "test 1", "ot"),
            BookData(10, "test 10", "nt"),
        ), object : BookDataToDomainMapper {
            override fun map(id: Int, name: String): BookDomain {
                return BookDomain.Base(id, name)
            }
        })

        val actual = domain.map(object : BooksDomainToUiMapper {
            override fun map(books: List<BookDomain>): BooksUi {
                return BooksUi.Success(books, mapper)
            }

            override fun map(errorType: ErrorType): BooksUi {
                throw IllegalStateException()
            }
        })

        val expected = BooksUi.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "test 1"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(10, "test 10"),

                ), mapper)
        assertEquals(expected, actual)
    }

}
