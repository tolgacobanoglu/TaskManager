package com.tolgacobanoglu.taskmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tolgacobanoglu.taskmanager.adapter.TaskAdapter
import com.tolgacobanoglu.taskmanager.database.TaskDatabase
import com.tolgacobanoglu.taskmanager.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.fragment_tasks.*

class MainActivity : AppCompatActivity()
{

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawer = binding.drawerLayout

        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)

        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController,appBarConfiguration)

        val navView = binding.navView
        NavigationUI.setupWithNavController(navView,navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }


}

