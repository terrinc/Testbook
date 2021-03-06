package com.terrinc.testbook.presentation

import com.terrinc.testbook.core.Abstract

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper> {

    override fun map(mapper: StringMapper) = Unit

    object Process : BookUi()

    abstract class Info(
        private val id: Int,
        private val name: String,
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
    }

    class Base(id: Int, name: String) : Info(id, name)

    class Testament(id: Int, name: String) : Info(id, name)

    class Fail(
        private val message: String,
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    // todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }
}
