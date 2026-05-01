package com.montyblank.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.repository.BookRepository

class HomeViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>()
        val books : LiveData<List<BookEntity>> get() = _books

    private val repository = BookRepository()
    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }
}