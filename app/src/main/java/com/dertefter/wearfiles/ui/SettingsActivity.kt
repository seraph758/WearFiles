package com.dertefter.wearfiles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.widget.WearableLinearLayoutManager
import com.dertefter.wearfiles.R
import com.dertefter.wearfiles.common.CustomScrollingLayoutCallback
import com.dertefter.wearfiles.common.ThemeEngine
import com.dertefter.wearfiles.databinding.ActivityFilesBinding
import com.dertefter.wearfiles.model.SettingsItem
import com.dertefter.wearfiles.model.SettingsItemType
import com.dertefter.wearfiles.ui.adapter.SettingsAdapter
import com.dertefter.wearfiles.ui.adapter.ThemesAdapter

class SettingsActivity : AppCompatActivity() {
    private lateinit var adapter: SettingsAdapter
    private lateinit var binding: ActivityFilesBinding
    var themeWasSelected: Int? = null

    override fun onResume() {
        super.onResume()
        val selectedTheme = ThemeEngine.getSelectedTheme()
        if (selectedTheme != themeWasSelected){
            recreate()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ThemeEngine.setup(this)
        val selectedTheme = ThemeEngine.getSelectedTheme()
        if (selectedTheme == 0) {
            setTheme(R.style.RoyalTheme)
            themeWasSelected = R.style.RoyalTheme
        } else {
            setTheme(selectedTheme)
            themeWasSelected = selectedTheme
        }



        binding = ActivityFilesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val settingsList = listOf(
            SettingsItem(SettingsItemType.HEADER),
            SettingsItem(SettingsItemType.WEB, url = "https://github.com/dertefter/WearFiles"),
            SettingsItem(SettingsItemType.SETTINGS),
            SettingsItem(SettingsItemType.THEME_ACTIVITY),
            SettingsItem(SettingsItemType.FOOTER)
        )

        adapter = SettingsAdapter(this, settingsList)



        binding.rv.adapter = adapter
        binding.rv.requestFocus()



    }

}
