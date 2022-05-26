package com.tolgacobanoglu.taskmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tolgacobanoglu.taskmanager.databinding.FragmentHelpBinding


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentHelpBinding.inflate(inflater,container,false)
        val view = binding.root



        return view
    }

}