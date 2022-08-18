package com.example.animequotesproject.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.animequotesproject.databinding.FragmentQuotePageBinding
import com.example.animequotesproject.presentation.adapter.QuoteAdapter
import com.example.animequotesproject.presentation.mvvm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuotePageFragment : Fragment() {

    private lateinit var binding: FragmentQuotePageBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuotePageBinding.inflate(layoutInflater, container, false)

//        binding.quoteRecycler.adapter = QuoteAdapter(list!!)

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getRandomQuotes()

        viewModel.randomQuotes.observe(viewLifecycleOwner) {
            binding.quoteRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.quoteRecycler.adapter = QuoteAdapter(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        binding.swipeContainer.setOnRefreshListener(OnRefreshListener {
            makeNewRequest()
            binding.swipeContainer.isRefreshing = false
        })

        return binding.root
    }

    private fun makeNewRequest() {
        viewModel.getRandomQuotes()
    }
}