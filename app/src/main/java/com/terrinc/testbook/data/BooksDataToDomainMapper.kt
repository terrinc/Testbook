package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.domain.BookDomain
import java.lang.Exception

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(books: List<Book>): BookDomain
    fun map(exception: Exception): BookDomain
}
