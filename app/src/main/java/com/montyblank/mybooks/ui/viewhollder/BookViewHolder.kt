package com.montyblank.mybooks.ui.viewhollder

import androidx.recyclerview.widget.RecyclerView
import com.montyblank.mybooks.databinding.ItemBookBinding
import com.montyblank.mybooks.entity.BookEntity

class BookViewHolder(private val item: ItemBookBinding) : RecyclerView.ViewHolder(item.root) {

    fun bind( book: BookEntity){
       item.textviewTitle.text = book.title
        item.textviewGenre.text = book.genre
        item.textviewAuthor.text = book.author



    }
}