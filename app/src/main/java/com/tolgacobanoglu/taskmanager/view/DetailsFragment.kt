package com.tolgacobanoglu.taskmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tolgacobanoglu.taskmanager.R
import com.tolgacobanoglu.taskmanager.database.TaskDatabase
import com.tolgacobanoglu.taskmanager.databinding.FragmentDetailsBinding
import com.tolgacobanoglu.taskmanager.databinding.FragmentTasksBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View?
    {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        val view = binding.root

        val taskID = DetailsFragmentArgs.fromBundle(requireArguments()).taskID
        val taskContent = DetailsFragmentArgs.fromBundle(requireArguments()).taskContent
        val taskTitle = DetailsFragmentArgs.fromBundle(requireArguments()).taskTitle

        binding.id.text = taskID.toString()
        binding.content.text = taskContent
        binding.title.text = taskTitle

        return view
    }

}