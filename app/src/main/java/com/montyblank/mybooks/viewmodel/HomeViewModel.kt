package com.montyblank.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.repository.BookRepository

class HomeViewModel(aplication: Application) : AndroidViewModel(aplication) {

    private val repository = BookRepository.getInstance(aplication.applicationContext)
    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> get() = _books




    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }

    fun favoriteBook(id: Int) {
        repository.toggleFavorite(id)
    }
}