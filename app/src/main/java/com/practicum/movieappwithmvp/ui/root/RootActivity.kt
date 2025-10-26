package com.practicum.movieappwithmvp.ui.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
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


    }

}