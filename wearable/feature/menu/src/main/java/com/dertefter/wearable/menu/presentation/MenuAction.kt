package com.dertefter.wearable.menu.presentation

data class MenuAction(
    val type: MenuActionType,
    val paths: List<String>
)
