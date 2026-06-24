package com.montyblank.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.montyblank.mybooks.R
import com.montyblank.mybooks.databinding.FragmentFavoriteBinding
import com.montyblank.mybooks.helper.BookConstants
import com.montyblank.mybooks.ui.adapter.BookAdapter
import com.montyblank.mybooks.ui.listener.BookListener
import com.montyblank.mybooks.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val favoriteViewModel : FavoriteViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.recyclerviewBooksFavorite.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBooksFavorite.adapter = adapter

        attacherListener()

        setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attacherListener() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {

                val bundle = Bundle()
                bundle.putInt(BookConstants.KEY.BOOK_ID, id)

                //navegar para detalhes
                findNavController().navigate(R.id.navigation_details, bundle)

            }

            override fun onFavoriteChange(id: Int) {
                favoriteViewModel.favoriteBook(id)
            }

        })
    }

    private fun setObservers() {
        favoriteViewModel.bookList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recyclerviewBooksFavorite.visibility = View.GONE
                binding.textviewNoBooks.visibility = View.VISIBLE
                binding.imageviewNoBooks.visibility = View.VISIBLE
            }else{
                binding.recyclerviewBooksFavorite.visibility = View.VISIBLE
                binding.textviewNoBooks.visibility = View.GONE
                binding.imageviewNoBooks.visibility = View.GONE
            adapter.updateBooks(it)
            }
        }
    }
}