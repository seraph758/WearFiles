package com.dertefter.wearfiles.model

enum class SettingsItemType {
    HEADER,
    WEB,
    SETTINGS,
    THEME_ACTIVITY,
    FOOTER
}

data class SettingsItem(val type: SettingsItemType, val url: String? = null)