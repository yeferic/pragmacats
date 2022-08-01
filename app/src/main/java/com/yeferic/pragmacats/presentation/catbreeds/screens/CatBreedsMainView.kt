package com.yeferic.pragmacats.presentation.catbreeds.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yeferic.pragmacats.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatBreedsMainView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_cat_breeds)

        var fragment = supportFragmentManager.findFragmentByTag(CatsBreedsFragment.FRAGMENT_NAME)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    CatsBreedsFragment.newInstance(),
                    CatsBreedsFragment.FRAGMENT_NAME
                ).commitAllowingStateLoss()
        }
    }
}