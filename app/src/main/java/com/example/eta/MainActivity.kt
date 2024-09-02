package com.example.eta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eta.ui.theme.EtaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EtaTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "alpha"
                ) {
                    composable("alpha") {
                        AlphaScreen(navController)
                    }
                    composable("beta") {
                        BetaScreen(navController)
                    }
                    composable("gamma") {
                        GammaScreen(navController)
                    }
                    composable("delta") {
                        DeltaScreen(navController)
                    }

                }
            }
        }
    }
}

@Composable
fun AlphaScreen(navController: NavController) {
    val (value, setValue) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("독도의용수비대 기념사업회")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigate("beta")
            }
        ) {
            Text("a")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigate("gamma")
            }
        ) {
            Text("b")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigate("delta")
            }
        ) {
            Text("c")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigate("epsilon")
            }
        ) {
            Text("d")
        }
    }
}

@Composable
fun BetaScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("첫 번째 화면")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text("뒤로 가기")
        }
        Greetings()
    }
}

@Composable
fun GammaScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("두 번째 화면")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text("뒤로 가기")
        }
    }
}

@Composable
fun DeltaScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("세 번째 화면")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = {
                navController.navigateUp()
            }
        ) {
            Text("뒤로 가기")
        }
    }
}


@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) {name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var isFavorite by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .padding(24.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "안녕!!")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            if (expanded) {
                Text(
                    text = ("독도는 우리 땅! ").repeat(4),
                )
            }
        }
        IconButton(onClick = {
            isFavorite = !isFavorite
        }) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "favorite",
                tint = Color.White)
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}