package com.finalproject.utilities

import com.finalproject.AvailableBares
import com.finalproject.ProfilePage
import com.finalproject.ReservationPage
import com.finalproject.SettingsMenu

class LayoutUtils {
    private val settingsLayoutClasses: Map<String, Class<*>?>
    private val profileLayoutClasses: Map<String, Class<*>?>
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
        this.profileLayoutClasses = mapOf(
            "COVID-19 Guidelines" to null,
            "Favorites" to null,
        )
        this.homeLayoutClasses = mapOf(
            1 to AvailableBares::class.java,
            2 to ReservationPage::class.java,
            3 to SettingsMenu::class.java,
            0 to ProfilePage::class.java
        )
    }

    fun getProfileLayoutClasses(): Map<String, Class<*>?> {
        return this.profileLayoutClasses
    }

    fun getSettingLayoutClasses(): Map<String, Class<*>?> {
        return this.settingsLayoutClasses
    }

    fun getHomeLayoutClasses(): Map<Int, Class<*>?> {
        return this.homeLayoutClasses
    }
}