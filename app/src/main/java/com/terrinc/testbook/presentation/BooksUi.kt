package com.terrinc.testbook.presentation

import com.terrinc.testbook.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    data class Success(
        private val books: List<BookUi>,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(books)
    }

    data class Fail(
        private val message: String,
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) = mapper.map(listOf(BookUi.Fail(message)))
    }
}
