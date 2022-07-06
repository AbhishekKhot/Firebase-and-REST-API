package com.example.task.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.R
import com.example.task.adapter.ArticleAdapter
import com.example.task.databinding.FragmentNewsBinding
import com.example.task.databinding.FragmentSignupBinding
import com.example.task.ui.MainActivity
import com.example.task.ui.NewsArticleViewModel
import com.example.task.util.Resource

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NewsArticleViewModel
    private val articleAdapter = ArticleAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        binding.recyclerView.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }


        viewModel.getNewsArticles.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Error -> {
                    binding.progresBar.visibility = View.INVISIBLE
                }
                is Resource.Success -> {
                    binding.progresBar.visibility = View.INVISIBLE
                    it.data?.let {
                        articleAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Loading -> {
                    binding.progresBar.visibility = View.VISIBLE
                }
            }
        })
    }
}