package com.terrinc.testbook.data

import com.terrinc.testbook.core.Abstract

interface ToBookMapper : Abstract.Mapper {

    fun map(id: Int, name: String, testament: String): BookData

    class Base : ToBookMapper {
        override fun map(id: Int, name: String, testament: String) = BookData(id, name, testament)
    }
}
