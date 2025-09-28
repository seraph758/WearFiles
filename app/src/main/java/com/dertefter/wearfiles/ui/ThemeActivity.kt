package com.dertefter.wearfiles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.widget.WearableLinearLayoutManager
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.common.CustomScrollingLayoutCallback
import com.dertefter.wearfiles.common.ThemeEngine
import com.dertefter.wearfiles.databinding.ActivityFilesBinding
import com.dertefter.wearfiles.ui.adapter.ThemesAdapter

class ThemeActivity : AppCompatActivity() {
    private lateinit var adapter: ThemesAdapter
    private lateinit var binding: ActivityFilesBinding
    var currentTheme: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFilesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ThemesAdapter(){ theme ->
            ThemeEngine.setSelectedTheme(theme)
            adapter.updateSelectedTheme(theme)
        }

        ThemeEngine.setup(this)
        val selectedTheme = ThemeEngine.getSelectedTheme()
        if (selectedTheme == 0) {
            currentTheme = R.style.RoyalTheme
        } else {
            currentTheme = selectedTheme
        }

        adapter.updateSelectedTheme(selectedTheme)


        binding.rv.adapter = adapter

        binding.rv.requestFocus()


        val themesList = listOf(
            R.style.RedTheme,
            R.style.OrangeTheme,
            R.style.YellowTheme,
            R.style.GreenTheme,
            R.style.TealTheme,
            R.style.LightBlueTheme,
            R.style.RoyalTheme,
            R.style.PinkTheme
        )

        adapter.updateThemesList(themesList)


    }

}
