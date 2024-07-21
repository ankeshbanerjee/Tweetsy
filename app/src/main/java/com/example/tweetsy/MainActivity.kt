package com.example.tweetsy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.tweetsy.screens.CategoriesScreen
import com.example.tweetsy.screens.TweetsScreen
import com.example.tweetsy.ui.theme.TweetsyTheme
import com.example.tweetsy.viewModels.CategoriesViewModel
import com.example.tweetsy.viewModels.TweetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TweetsyTheme {
                // A surface container using the 'background' color from the theme
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Category ){
        composable<Category>{
            val viewModel = hiltViewModel<CategoriesViewModel>()
            CategoriesScreen(navController, viewModel)
        }
        composable<Tweets> {backStackEntry->
            val viewModel = hiltViewModel<TweetsViewModel>()
            val tweets: Tweets = backStackEntry.toRoute()
            TweetsScreen(tweets, navController, viewModel)
        }
    }
}

@Serializable
object Category
@Serializable
data class Tweets(val category: String)
