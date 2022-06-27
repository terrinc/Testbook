package com.terrinc.testbook.domain

class BaseBookDataToDomainMapper : BookDataToDomainMapper {
    override fun map(id: Int, name: String) = BookDomain.Base(id, name)
}
