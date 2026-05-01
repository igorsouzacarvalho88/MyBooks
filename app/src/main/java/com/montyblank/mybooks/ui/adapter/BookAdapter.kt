package com.montyblank.mybooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.montyblank.mybooks.databinding.ItemBookBinding
import com.montyblank.mybooks.entity.BookEntity
import com.montyblank.mybooks.ui.viewhollder.BookViewHolder

class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {

    private var booksList: List<BookEntity> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int
    ) {
        holder.bind(booksList[position])
    }

    fun updateBooks(list: List<BookEntity>) {
        booksList = list
        notifyDataSetChanged()
    }
}