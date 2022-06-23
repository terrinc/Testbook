package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.domain.BooksDomain
import java.lang.Exception

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<BookData>): BooksDomain
    fun map(exception: Exception): BooksDomain
}
