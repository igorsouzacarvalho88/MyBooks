package com.montyblank.mybooks.ui.listener

interface BookListener {
    fun onClick(id: Int)
    fun onFavoriteChange(id: Int)
}