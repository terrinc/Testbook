package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {

    data class Base(
        private val id: Int,
        private val name: String,
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
    }

    data class Testament(private val type: TestamentType) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper) = type.map(mapper)
    }
}
