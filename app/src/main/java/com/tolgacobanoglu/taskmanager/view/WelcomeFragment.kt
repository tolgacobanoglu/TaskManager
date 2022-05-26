package com.tolgacobanoglu.taskmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tolgacobanoglu.taskmanager.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment()
{
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentWelcomeBinding.inflate(inflater,container,false)
        val view = binding.root


        return view
    }

}