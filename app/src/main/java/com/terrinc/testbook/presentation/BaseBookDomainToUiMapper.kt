package com.terrinc.testbook.presentation

import com.terrinc.testbook.R
import com.terrinc.testbook.domain.BookDomainToUiMapper
import com.terrinc.testbook.domain.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider) : BookDomainToUiMapper {
    override fun map(id: Int, name: String): BookUi {
        return when {
            TestamentType.NEW.matches(id) -> BookUi.Testament(id, resourceProvider.getString(R.string.new_testament))
            TestamentType.OLD.matches(id) -> BookUi.Testament(id, resourceProvider.getString(R.string.old_testament))
            else -> BookUi.Base(id, name)
        }
    }
}
