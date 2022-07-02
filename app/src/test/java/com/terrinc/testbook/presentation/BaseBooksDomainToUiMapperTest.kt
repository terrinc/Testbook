package com.terrinc.testbook.presentation

import com.terrinc.testbook.R
import com.terrinc.testbook.domain.BookDomainToUiMapper
import com.terrinc.testbook.domain.ErrorType
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * test for [BaseBooksDomainToUiMapper]
 */
class BaseBooksDomainToUiMapperTest {

    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                throw IllegalStateException("not use here")
            }
        })
        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Fail("noconnection")
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Fail("serviceunavailable")
        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Fail("somethingwentwrong")
        assertEquals(expected, actual)

    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int) = when (id) {
            R.string.no_connection_message -> "noconnection"
            R.string.service_unavailable_message -> "serviceunavailable"
            else -> "somethingwentwrong"
        }
    }
}
