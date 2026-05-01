package com.montyblank.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.montyblank.mybooks.databinding.FragmentHomeBinding
import com.montyblank.mybooks.ui.adapter.BookAdapter
import com.montyblank.mybooks.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModels()
    private val adapter : BookAdapter = BookAdapter(
    )



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)

        //adpter
        binding.recyclerviewBooks.adapter = adapter

        viewModel.getAllBooks()

       setObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers(){
        viewModel.books.observe(viewLifecycleOwner){
            adapter.updateBooks(it)
        }
    }
}