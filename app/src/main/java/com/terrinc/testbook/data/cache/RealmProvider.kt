package com.terrinc.testbook.data.cache

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

interface RealmProvider {

    fun provide(): Realm

    class Base : RealmProvider {
        override fun provide(): Realm {
            val config = RealmConfiguration.create(schema = setOf(BookDb::class))
            return Realm.open(config)
        }
    }
}
