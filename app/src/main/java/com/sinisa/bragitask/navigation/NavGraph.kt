package com.sinisa.bragitask.navigation

import kotlinx.serialization.Serializable

sealed class NavGraph {

    @Serializable
    data object Movies : NavGraph()

    @Serializable
    data object Filters : NavGraph()
}
