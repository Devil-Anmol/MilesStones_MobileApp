//import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen(
    onSevenStopsClicked: () -> Unit,
    onFifteenStopsClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onSevenStopsClicked) {
            Text(text = "7 Stops")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onFifteenStopsClicked) {
            Text(text = "15 Stops")
        }
    }
}
//@Preview
@Composable
fun SevenStopsScreen() {
    var distanceInKms by remember { mutableStateOf(0f) }
    var distanceInMiles by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }
    var currentStopIndex by remember { mutableStateOf(0) }
    val num = arrayOf(10,20,30,25,16,24,35);
    val sum = 150
    var checked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ){
            if(checked) {
                Text(
                    "Total Distance: $distanceInKms km",
                    modifier = Modifier
                        .weight(0.7f)
                        .align(Alignment.CenterVertically)
                )
            }
            else {

                Text(
                "Total Distance: $distanceInMiles miles",
                modifier = Modifier
                    .weight(0.7f)
                    .align(Alignment.CenterVertically)
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = progress / 150, // assuming progress is in percentage
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // List of 7 stops
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            StopCard(stopName = "Start", isCurrentStop = currentStopIndex==0,0)
            repeat(6) { index ->
                StopCard(
                    stopName = "Stop ${index + 1}",
                    isCurrentStop = index == currentStopIndex - 1,
                    num[index]
                )
            }
            StopCard(stopName = "Destination", isCurrentStop = currentStopIndex==7,num[6])
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = {
            currentStopIndex++;
            progress+=num[currentStopIndex-1];
            distanceInKms+=num[currentStopIndex-1];
            distanceInMiles = distanceInKms * 1.6f;
            // Handle Next Stop click and update state
        }, enabled = currentStopIndex<7) {
            Text(text = "Next Stop")
        }
    }
}

@Composable
fun FifteenStopsScreen() {
    var distanceInKms by remember { mutableStateOf(0f) }
    var distanceInMiles by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }
    var currentStopIndex by remember { mutableStateOf(0) }
    val num = arrayOf(10, 20, 30, 25, 16, 24, 35, 18, 22, 27, 21, 29, 33, 26, 19)
    val sum = 355
    var checked by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(90.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            if (checked) {
                Text(
                    "Total Distance: $distanceInKms km",
                    modifier = Modifier
                        .weight(0.7f)
                        .align(Alignment.CenterVertically)
                )
            } else {
                Text(
                    "Total Distance: $distanceInMiles miles",
                    modifier = Modifier
                        .weight(0.7f)
                        .align(Alignment.CenterVertically)
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LinearProgressIndicator(
            progress = progress / sum, // assuming progress is in percentage
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // List of 15 stops
        Box (
            modifier = Modifier
        ){
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.8f),
                    contentPadding = PaddingValues(horizontal = 5.dp)
                ) {
                    item {
                        StopCard(
                            stopName = "Start",
                            isCurrentStop = currentStopIndex == 0,
                            dist = 0
                        )
                    }
                    items(num.size - 1 ) { index ->
                        StopCard(
                            stopName = "Stop ${index + 1}",
                            isCurrentStop = index == currentStopIndex - 1,
                            dist = num[index]
                        )
                    }
                    item {
                        StopCard(
                            stopName = "Destination",
                            isCurrentStop = currentStopIndex == 15,
                            dist = num.last()
                        )
                    }
                }
//        Spacer(modifier = Modifier.height(5.dp))
                Button(
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        currentStopIndex++
                        progress += num[currentStopIndex - 1]
                        distanceInKms += num[currentStopIndex - 1]
                        distanceInMiles = distanceInKms * 1.6f
                        // Handle Next Stop click and update state
                    },
                    enabled = currentStopIndex < 15
                ) {
                    Text(text = "Next Stop")
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}


@Composable
fun StopCard(stopName: String, isCurrentStop: Boolean = false,dist: Int) {
    val backgroundColor = if (isCurrentStop) Color.LightGray else Color.White
    val contentColor = if (isCurrentStop) Color.Black else Color.Gray
    var m: Float = dist*1.6f

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp)
            .background(color = backgroundColor),
//        elevation = CardElevation(4.dp)
    ) {
        Text(
            text = stopName,
            modifier = Modifier.padding(2.dp),
            color = contentColor
        )
        Text(
            text = "Distance from last stop: $dist kms, $m miles",
            modifier = Modifier.padding(2.dp),
            color = contentColor
            )
    }
}