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
import com.montyblank.mybooks.databinding.FragmentHomeBinding
import com.montyblank.mybooks.helper.BookConstants
import com.montyblank.mybooks.ui.adapter.BookAdapter
import com.montyblank.mybooks.ui.listener.BookListener
import com.montyblank.mybooks.viewmodel.HomeViewModel

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
        binding.recyclerviewBooks.adapter = adapter

        attacherListener()

       setObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBooks()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attacherListener(){
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {

                val bundle = Bundle()
                bundle.putInt(BookConstants.KEY.BOOK_ID, id)

                //navegar para detalhes
                findNavController().navigate(R.id.navigation_details,bundle)

            }

        })
    }

    private fun setObservers(){
        viewModel.books.observe(viewLifecycleOwner){
            adapter.updateBooks(it)
        }
    }
}