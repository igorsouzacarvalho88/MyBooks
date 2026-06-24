package com.montyblank.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.repository.BookRepository
import kotlinx.coroutines.launch

class HomeViewModel(aplication: Application) : AndroidViewModel(aplication) {

    private val repository = BookRepository.getInstance(aplication.applicationContext)

    val bookList: LiveData<List<BookEntity>> get() = repository.getAllBooks().asLiveData()


    fun favoriteBook(id: Int) {

        viewModelScope.launch {
            repository.toggleFavorite(id)
        }


    }
}