package com.terrinc.testbook.data

abstract class BaseBooksRepositoryTest {
    protected inner class TestToBookMapper : ToBookMapper {
        override fun map(id: Int, name: String) = BookData(id, name)
    }
}
