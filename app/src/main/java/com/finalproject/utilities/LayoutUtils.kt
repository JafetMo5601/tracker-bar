package com.finalproject.utilities

import com.finalproject.SettingsMenu

class LayoutUtils {
    private val settingsLayoutClasses: Map<String, Class<*>?>
    private val homeLayoutClasses: Map<Int, Class<*>?>

    init {
        this.settingsLayoutClasses = mapOf(
            "Profile" to null,
            "Appearance" to null,
            "Functions" to null,
            "Notifications" to null,
            "Contact and support" to null,
            "Sign out" to null
        )
        this.homeLayoutClasses = mapOf(
            1 to null,
            2 to null,
            3 to SettingsMenu::class.java,
            0 to null
        )
    }

    fun getSettingLayoutClasses(): Map<String, Class<*>?> {
        return this.settingsLayoutClasses
    }

    fun getHomeLayoutClasses(): Map<Int, Class<*>?> {
        return this.homeLayoutClasses
    }
}