package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<BookDomain>): BooksUi
    fun map(errorType: ErrorType): BooksUi
}
