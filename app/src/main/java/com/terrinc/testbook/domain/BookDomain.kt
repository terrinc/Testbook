package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.presentation.BookUi

class BookDomain(private val id: Int, private val name: String): Abstract.Object<BookUi, BookDomainToUiMapper> {
    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
}
