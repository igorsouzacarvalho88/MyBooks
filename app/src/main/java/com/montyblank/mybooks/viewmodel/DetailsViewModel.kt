package com.montyblank.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.repository.BookRepository

class DetailsViewModel : ViewModel() {

    private val repository: BookRepository = BookRepository()

    private val _book = MutableLiveData<BookEntity>()
    val book : LiveData<BookEntity> = _book

    fun getBookById(id: Int) {
        _book.value = repository.getBookById(id)

    }
}