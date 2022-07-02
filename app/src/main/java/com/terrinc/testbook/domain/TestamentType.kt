package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract
import com.terrinc.testbook.core.Matcher
import com.terrinc.testbook.presentation.BookUi

enum class TestamentType(private val id: Int): Abstract.Object<BookUi, BookDomainToUiMapper>, Matcher<Int> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun map(mapper: BookDomainToUiMapper) = mapper.map(id, name)
    override fun matches(arg: Int) = this.id == arg
}
