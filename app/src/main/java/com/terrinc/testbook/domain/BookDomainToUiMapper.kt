package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Book
import com.terrinc.testbook.presentation.BooksUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<Book>): BooksUi
    fun map(errorType: ErrorType): BooksUi
}
