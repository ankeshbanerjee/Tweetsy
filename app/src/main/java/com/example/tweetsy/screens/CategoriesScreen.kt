package com.example.tweetsy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tweetsy.viewModels.CategoriesViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tweetsy.Tweets
import com.example.tweetsy.ui.theme.Purple40
import com.example.tweetsy.utils.Constants

@Composable
fun CategoriesScreen(navController: NavController, viewModel: CategoriesViewModel){
    val isLoading = viewModel.isLoading.collectAsState()
    val categories = viewModel.categories.collectAsState()
    CategoriesScreenContent(isLoading, categories, {category ->
        navController.navigate(route = Tweets(category))
    })
}

@Composable
fun CategoriesScreenContent(isLoading: State<Boolean>, categories: State<List<String>>, onCategoryItemClick: (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp).height(140.dp)){
            Surface(
                color= Purple40,
                shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.TopCenter)
            ){}
            Box(
                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .shadow(10.dp, shape = RoundedCornerShape(20.dp))
//                    .fillMaxWidth(.7f)
//                    .background(color = Color.White, shape = RoundedCornerShape(20.dp))
//                    .height(80.dp)
//                    .align(Alignment.BottomCenter)
                modifier = Modifier
                    .shadow(10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .fillMaxWidth(.7f)
                    .height(80.dp)
                    .align(Alignment.BottomCenter)
            ){
                Text("Tweetsy", fontSize = 24.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Medium)
            }
            
        }
        if (isLoading.value){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text("Loading...")
            }
        }else{
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 180.dp),
                modifier = Modifier.padding(horizontal = 8.dp)

            ) {
                items(categories.value){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(155.dp)
                            .padding(8.dp)
                            .shadow(elevation = 10.dp)
                            .background(shape = RoundedCornerShape(8.dp), color = Color.White)
                            .clickable { onCategoryItemClick(it) }

                    ){
                        AsyncImage(model = Constants.CARD_BG,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,

                            modifier = Modifier
                                .fillMaxWidth()
                                .height(105.dp)
                                .padding(bottom = 8.dp)
                                .clip(shape = RoundedCornerShape(8.dp))
                        )
                        Text(it, textAlign = TextAlign.Center, fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CategoriesScreenPreview(){
    CategoriesScreenContent(isLoading = rememberSaveable{ mutableStateOf<Boolean>(false) }, categories = rememberSaveable{
        mutableStateOf(
            listOf(
                "Technology",
                "Science",
                "Space",
                "Health",
                "Sports",
                "Politics",
                "Entertainment",
            ))
    }){}
}