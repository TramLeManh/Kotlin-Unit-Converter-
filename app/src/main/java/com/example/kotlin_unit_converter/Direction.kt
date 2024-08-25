package com.example.kotlin_unit_converter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlin_unit_converter.ui.theme.Kotlin_Unit_ConverterTheme
import kotlin.random.Random

class Direction : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kotlin_Unit_ConverterTheme {}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Test();
}

@Composable
fun Test() {

    val directions = listOf("North", "South", "East", "West");
    var n by remember {
        mutableIntStateOf(0)
    }
    var direct by remember {
        mutableStateOf(" ")
    }
    val action = remember {
        mutableStateOf(" ")
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Treasure found: $n",
        )
        Text(
            "Go $direct" ,
        )

        Text(
            action.value
        )
        Box {
            Column {
                directions.forEach {
                    Button(onClick = {
                        direct = it;
                        if (Random.nextBoolean()) {
                            n += 1;
                            action.value = " Found a treasure"
                        } else {
                            action.value = " Hit a storm"
                        }

                    }) {
                        Text("Go $it")
                    }
                }
            }
        }
    }
}