package com.sportunity.marathon

import com.sportunity.marathon.ConstantAppScreenName.ID_ARGUMENT


sealed class AppScreen(val route: String) {
    data object MarathonEventsScreen : AppScreen(route = ConstantAppScreenName.MARATHON_EVENTS_SCREEN)
    data object MarathonRaceScreen : AppScreen(route = ConstantAppScreenName.MARATHON_RACE_SCREEN) {
        fun passId(id: Int): String {
            return this.route.replace(oldValue = "{$ID_ARGUMENT}", newValue = id.toString())
        }
    }
}

object ConstantAppScreenName {
    const val ID_ARGUMENT = "id"
    const val MARATHON_EVENTS_SCREEN = "home_screen"
    const val MARATHON_RACE_SCREEN = "detail_screen/{$ID_ARGUMENT}"
}