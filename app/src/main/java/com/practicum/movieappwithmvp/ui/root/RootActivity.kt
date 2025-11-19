package com.practicum.movieappwithmvp.ui.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.practicum.movieappwithmvp.R
import com.practicum.movieappwithmvp.core.navigation.NavigatorHolder
import com.practicum.movieappwithmvp.core.navigation.NavigatorImpl
import com.practicum.movieappwithmvp.databinding.ActivityRootBinding
import com.practicum.movieappwithmvp.ui.movies.MoviesFragment
import org.koin.android.ext.android.inject

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

//    // Заинжектили NavigatorHolder,
//    // чтобы прикрепить к нему Navigator
//    private val navigatorHolder: NavigatorHolder by inject()
//
//    // Создали Navigator
//    private val navigator = NavigatorImpl(
//        fragmentContainerViewId = R.id.rootFragmentContainerView,
//        fragmentManager = supportFragmentManager
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Привязываем вёрстку к экрану
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailsFragment, R.id.moviesCastFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

    }
    fun animateBottomNavigationView() {
        binding.bottomNavigationView.visibility = View.GONE
    }

}