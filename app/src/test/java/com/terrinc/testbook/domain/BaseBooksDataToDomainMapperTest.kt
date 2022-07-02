package com.terrinc.testbook.domain

import com.terrinc.testbook.data.BookData
import com.terrinc.testbook.data.BooksData
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapperTest {
    @Test
    fun test_success() {

        val data = BooksData.Success(listOf(
            BookData(1, "book 1", "ot"),
            BookData(2, "book 2", "ot"),
            BookData(3, "book 3", "nt"),
            BookData(4, "book 4", "nt"),
        ))

        val actual = data.map(BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper()))

        val expected = BooksDomain.Success(
            listOf(
                BookDomain.Testament(TestamentType.OLD),
                BookDomain.Base(1, "book 1"),
                BookDomain.Base(2, "book 2"),
                BookDomain.Testament(TestamentType.NEW),
                BookDomain.Base(3, "book 3"),
                BookDomain.Base(4, "book 4"),
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun test_fail() {
        val mapper = BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        var actual = mapper.map(UnknownHostException())
        var expected = BooksDomain.Fail(ErrorType.NO_CONNECTION)
        assertEquals(expected, actual)

        actual = mapper.map(IllegalStateException())
        expected = BooksDomain.Fail(ErrorType.GENERIC_ERROR)
        assertEquals(expected, actual)
    }
}
