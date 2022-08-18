package com.example.animequotesproject.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animequotesproject.*
import com.example.animequotesproject.databinding.FragmentMainPageBinding
import com.example.animequotesproject.presentation.adapter.AnimeAdapter
import com.example.animequotesproject.presentation.mvvm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment() {

    private lateinit var binding: FragmentMainPageBinding
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainPageBinding.inflate(layoutInflater, container, false)

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.availableAnime.observe(viewLifecycleOwner) {
            binding.animeRecycler.layoutManager = GridLayoutManager(requireContext(), 3)
            val adapter = AnimeAdapter(it)
            binding.animeRecycler.adapter = adapter
            adapter.setOnItemClickListener(object : AnimeAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val bundle = Bundle()
                    val item = it[position]
                    bundle.putString("animeTitle", item)

                    val temp = SecondQuotePageFragment()
                    temp.arguments = bundle

                    activity!!.supportFragmentManager.beginTransaction().replace(R.id.frame_layout, temp).commit()
                }
            })
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

        viewModel.getAllAvailableAnime()
        return binding.root
    }
}