package com.terrinc.testbook.presentation

import com.terrinc.testbook.domain.BookDomainToUiMapper

class BaseBookDomainToUiMapper: BookDomainToUiMapper {
    override fun map(id: Int, name: String) = BookUi.Base(id, name)
}
