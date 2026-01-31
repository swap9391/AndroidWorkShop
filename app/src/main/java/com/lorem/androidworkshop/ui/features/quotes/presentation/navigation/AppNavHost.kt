package com.lorem.androidworkshop.ui.features.quotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lorem.androidworkshop.ui.features.quotes.domain.model.Quote
import com.lorem.androidworkshop.ui.features.quotes.presentation.screens.QuoteDetailScreen
import com.lorem.androidworkshop.ui.features.quotes.presentation.screens.QuoteListScreen

@Composable
fun NavHostController.AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.QuoteList.route,
) {

    NavHost(
        modifier = modifier,
        navController = this,
        startDestination = startDestination
    ){

        composable(NavigationItem.QuoteList.route) {
            QuoteListScreen(onQuoteClick = {
                navigate(NavigationItem.QuoteDetail.route)
            })
        }

        composable(NavigationItem.QuoteDetail.route){
            QuoteDetailScreen(Quote(1,"Quote 1", "Author 1")) {
                popBackStack()
            }
        }


    }
}
