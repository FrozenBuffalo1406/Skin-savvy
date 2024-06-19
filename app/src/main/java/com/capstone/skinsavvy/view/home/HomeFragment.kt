package com.capstone.skinsavvy.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.skinsavvy.R
import com.capstone.skinsavvy.adapter.ImageAdapter
import com.capstone.skinsavvy.databinding.FragmentHomeBinding
import com.capstone.skinsavvy.factory.MainViewModelFactory
import com.capstone.skinsavvy.ui.ui.home.HomeViewModel

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        val imageAdapter = ImageAdapter()
        binding.rvImageslider.adapter = imageAdapter

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageSlider: ImageView = view.findViewById(R.id.rv_imageslider)
        imageSlider.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.navigation_home) {
            val categoryFragment = HomeFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.navHost, categoryFragment, HomeFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }
}

