package com.terrinc.testbook.domain

import com.terrinc.testbook.core.Abstract

interface BookDataToDomainMapper: Abstract.Mapper {

    fun map(id: Int, name: String): BookDomain
}
