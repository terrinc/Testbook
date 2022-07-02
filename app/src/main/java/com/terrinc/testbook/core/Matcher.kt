package com.terrinc.testbook.core

interface Matcher<T> {
    fun matches(arg: T): Boolean
}
