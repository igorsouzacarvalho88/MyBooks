package com.montyblank.mybooks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.repository.BookRepository

class FavoriteViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> get() = _books

    private val repository = BookRepository.getInstance()


    fun getFavoriteBooks() {
        _books.value = repository.getFavoriteBooks()
    }

    fun favoriteBook(id: Int) {
        repository.toggleFavorite(id)
    }
}