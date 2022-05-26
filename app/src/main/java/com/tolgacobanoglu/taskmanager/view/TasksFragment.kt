package com.tolgacobanoglu.taskmanager.view

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tolgacobanoglu.taskmanager.adapter.TaskAdapter
import com.tolgacobanoglu.taskmanager.database.TaskDatabase
import com.tolgacobanoglu.taskmanager.databinding.FragmentTasksBinding
import com.tolgacobanoglu.taskmanager.model.TasksViewModel

class TasksFragment : Fragment()
{

    private var _binding: FragmentTasksBinding? = null
    private lateinit var database: TaskDatabase
    private val binding get() = _binding!!
    private lateinit var  viewModel : TasksViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database= TaskDatabase.getDatabase(requireContext())
        viewModel = TasksViewModel(application = Application(), database)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        _binding = FragmentTasksBinding.inflate(inflater,container,false)
        val view = binding.root

        setupRecyclerView()
        binding.floatingActionButton.setOnClickListener{

            val action = TasksFragmentDirections.actionTasksFragmentToAddNewTaskFragment()
            view.findNavController().navigate(action)

        }



        return binding.root
    }

    private fun setupRecyclerView()
    {
        val list = viewModel.tasks
        list.observe(viewLifecycleOwner) {
            binding.tasksRecycler.adapter = list.value?.let { TaskAdapter(it.toMutableList()) }
        }
        binding.tasksRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

}