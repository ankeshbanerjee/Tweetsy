package com.example.tweetsy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tweetsy.Tweets
import com.example.tweetsy.models.Tweet
import com.example.tweetsy.viewModels.TweetsViewModel

@Composable
fun TweetsScreen(tweets: Tweets,navController: NavController,viewModel: TweetsViewModel) {
    val currentTweets = viewModel.tweets.collectAsState()
    val isLoading = viewModel.isLoading.collectAsState()
    val category = tweets
    TweetsScreenContent(tweets.category, isLoading, currentTweets)
}

@Composable
fun TweetsScreenContent(category: String, isLoading: State<Boolean>, tweets: State<List<Tweet>>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)

    ){
        Text(text = "$category Tweets", textAlign = TextAlign.Center, fontWeight = FontWeight.Medium, fontSize = 20.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp))
        if(isLoading.value){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Loading...")
            }
        }else{
            LazyColumn(
                modifier = Modifier.padding(horizontal = 10.dp)
            ){
                items(tweets.value){tweet->
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .shadow(10.dp)
                    ) {
                        Text(text = tweet.tweet, textAlign = TextAlign.Center, modifier= Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 6.dp) )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TweetsScreenPreview() {
    TweetsScreenContent("Motivation",
        isLoading = rememberSaveable{ mutableStateOf(false) },
        tweets = rememberSaveable{ mutableStateOf(listOf(
            Tweet(category = "Health", tweet = "Tweet 1"),
            Tweet(category = "Health", tweet = "Tweet 1"),
            Tweet(category = "Health", tweet = "Tweet 1"),
            Tweet(category = "Health", tweet = "Tweet 1"),
            Tweet(category = "Health", tweet = "Tweet 1"),
        ))}
    )
}