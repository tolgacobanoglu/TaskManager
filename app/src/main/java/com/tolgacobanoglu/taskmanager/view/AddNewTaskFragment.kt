package com.tolgacobanoglu.taskmanager.view

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.tolgacobanoglu.taskmanager.database.Entity
import com.tolgacobanoglu.taskmanager.database.TaskDatabase
import com.tolgacobanoglu.taskmanager.databinding.FragmentAddNewTaskBinding
import com.tolgacobanoglu.taskmanager.model.Task
import com.tolgacobanoglu.taskmanager.model.TasksViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AddNewTaskFragment : Fragment() {

    private var _binding: FragmentAddNewTaskBinding? = null
    private lateinit var viewModel: TasksViewModel
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentAddNewTaskBinding.inflate(inflater,container,false)
        val view = binding.root

        val database = TaskDatabase.getDatabase(requireContext())
        viewModel = TasksViewModel(application = Application(), database)


        var priority = "medium"

        binding.saveButton.setOnClickListener{
            if (binding.titleInput.text.toString().trim { it <= ' ' }.isNotEmpty() && binding.contentInput.text.toString().trim { it <= ' ' }.isNotEmpty())
            {
                var title = binding.titleInput.text.toString()
                var content = binding.contentInput.text.toString()
                viewModel.addData(Task(title, content, priority))
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title, content, priority))
                }

                val action = AddNewTaskFragmentDirections.actionAddNewTaskFragmentToTasksFragment()
                view.findNavController().navigate(action)


            }
            else
            {
                val toast = Toast.makeText(activity,"ERROR!!",Toast.LENGTH_SHORT)
                toast.show()
            }

        }


        binding.highChip.setOnClickListener{
            priority = "high"
            val snack = Snackbar.make(it,"HIGH PRIORITY",Snackbar.LENGTH_SHORT)
            snack.setAction("DEFAULT AS MEDIUM", View.OnClickListener {
                priority = "medium"
            })
            snack.show()
        }

        binding.mediumChip.setOnClickListener{
            priority = "medium"
        }

        binding.lowChip.setOnClickListener{
            priority = "low"
            val snack = Snackbar.make(it,"LOW PRIORITY",Snackbar.LENGTH_SHORT)
            snack.setAction("DEFAULT AS MEDIUM", View.OnClickListener {
                priority = "medium"
            })
            snack.show()
        }








        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}