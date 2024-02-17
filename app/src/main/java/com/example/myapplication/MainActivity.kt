package com.example.myapplication

import FifteenStopsScreen
import MainScreen
import SevenStopsScreen
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    WaterCounter();
                    AppNavigator();
//                    Greeting(
//                            name = "Android",
//                            modifier = Modifier.padding(innerPadding)
//                    )
//                    display("kms")

                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onSevenStopsClicked = {
                    navController.navigate("sevenStops")
                },
                onFifteenStopsClicked = {
                    navController.navigate("fifteenStops")
                }
            )
        }
        composable("sevenStops") {
            SevenStopsScreen();
        }
        composable("fifteenStops") {
            FifteenStopsScreen()
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            color = Color.Red,
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
    )
}

@Composable
fun image(){
    Image(
        painter = painterResource(id = R.drawable.photo),
        contentDescription = "dummy")
}

@Composable
fun progressbar(n:Float){
    LinearProgressIndicator(progress=n)
}
//@Preview(showBackground = true)
@Composable
fun display(unit: String){
    var n_kms by remember { mutableStateOf(0) }
    var n_miles by remember { mutableStateOf(0.0) }
    var clicks by remember { mutableStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (n_miles>0) Text(text = "Total Dist: $n_kms $unit")
        progressbar(n = 0.5f)
        Button(onClick = {
            n_kms++;
            n_miles+=1.6;
            clicks++;
        }, enabled = clicks<10) {
            Text(text = "increase")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    MyApplicationTheme {
////        Greeting("Android")
////        image()
//        progressbar(0.7f);
//    }
//    display("kms");
//    WaterCounter();
    show();
}

//import androidx.compose.foundation.layout.Row


@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses.")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }
    }
}
@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun show(){
    stops(1,19.0f,false);
}

@Composable
fun stops(i : Int, dist : Float,unit : Boolean){
    Card {
        Row {
//            Image(painter = painterResource(id = R.drawable.car), contentDescription = "car image")
            Column {
                Text(text = "Stop " + i)
                if(unit) Text(text = "next stop " + (dist*1.6))
                else Text(text = "next stop " + dist)
            }
        }
    }
}