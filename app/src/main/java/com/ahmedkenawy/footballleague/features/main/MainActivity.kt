package com.ahmedkenawy.footballleague.features.main

import android.os.Bundle
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import com.ahmedkenawy.footballleague.R
import com.ahmedkenawy.footballleague.core.base.BaseActivity
import com.ahmedkenawy.footballleague.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<Any>() {
    override val mTag = "MainActivity"
    override val mBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        setUpViews()
    }

    override fun setUpViews() {
        installSplashScreen()
        // prevent user from taking a screen shot or record the screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }


}