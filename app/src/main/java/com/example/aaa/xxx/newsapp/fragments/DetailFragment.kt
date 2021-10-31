package com.example.aaa.xxx.newsapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.aaa.xxx.newsapp.R
import com.example.aaa.xxx.newsapp.databinding.FragmentDetailBinding
import com.example.aaa.xxx.newsapp.util.downloadImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(R.layout.fragment_detail){
    val args:DetailFragmentArgs by navArgs()
    private lateinit var binding:FragmentDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentDetailBinding.bind(view)
        binding.tvDetailTitle.text=args.title
        binding.tvDescription.text=args.description
        binding.ivDetail.downloadImage(args.urlimage)
        val article=args.article
        btnWeb.setOnClickListener{
            val action=DetailFragmentDirections.actionDetailFragmentToWebFragment(article)
            Navigation.findNavController(it).navigate(action)
        }
    }
}