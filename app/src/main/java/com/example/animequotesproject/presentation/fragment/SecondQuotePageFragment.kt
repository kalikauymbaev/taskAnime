package com.example.animequotesproject.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotesproject.*
import com.example.animequotesproject.databinding.FragmentSecondQuotePageBinding
import com.example.animequotesproject.presentation.adapter.QuoteAdapter
import com.example.animequotesproject.presentation.mvvm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondQuotePageFragment : Fragment() {

    private lateinit var binding: FragmentSecondQuotePageBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondQuotePageBinding.inflate(layoutInflater, container, false)

        val title = arguments?.getString("animeTitle")
        Log.i("TAG123", "$title")


//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getAnimeByTitle(title!!)
        viewModel.animeTitle.observe(viewLifecycleOwner) {
            binding.secondQuoteRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.secondQuoteRecycler.adapter = QuoteAdapter(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            Log.i("TAG123", it)
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressDialogSecondQuote.visibility = View.VISIBLE
            } else {
                binding.progressDialogSecondQuote.visibility = View.GONE
            }
        })

        binding.btn128.setOnClickListener {
            val temp = MainPageFragment()
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout, temp).commit()
        }

        return binding.root
    }
}