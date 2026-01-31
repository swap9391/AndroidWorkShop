package com.lorem.androidworkshop.ui.features.quotes.presentation.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController

enum class Screen{
    QUOTELIST,
    QUOTEDETAIL
}

sealed class NavigationItem(val route: String){
    object QuoteList: NavigationItem(Screen.QUOTELIST.name)
    object QuoteDetail: NavigationItem(Screen.QUOTEDETAIL.name)
}

