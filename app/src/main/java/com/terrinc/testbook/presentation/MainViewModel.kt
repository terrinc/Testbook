package com.terrinc.testbook.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.terrinc.testbook.domain.BooksDomainToUiMapper
import com.terrinc.testbook.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
) : ViewModel() { // todo interface

    fun fetchBooks() {
        communication.map(listOf(BookUi.Process))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }
}
