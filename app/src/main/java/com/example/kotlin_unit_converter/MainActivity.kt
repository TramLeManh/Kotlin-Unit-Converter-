package com.example.kotlin_unit_converter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_unit_converter.ui.theme.Kotlin_Unit_ConverterTheme
import com.example.kotlin_unit_converter.ui.theme.h1
import com.example.kotlin_unit_converter.ui.theme.h2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Kotlin_Unit_ConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Widget()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        color = Color.Cyan,
    )
}

enum class TextStyleEnum {
    H1, H2
}
fun icon(expand:Boolean): ImageVector {
    if(expand) return Icons.Filled.KeyboardArrowDown
    return Icons.Filled.KeyboardArrowLeft
}
@Composable
fun Widget() {

    val textStyle = remember {
        mutableStateOf(h1);
    }
    var input by remember {
        mutableStateOf(" ");
    }
    var first by remember{
        mutableStateOf(false);
    }
    var second by remember{
        mutableStateOf(false);
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", style = textStyle.value)
        Spacer(modifier = Modifier.padding(8.dp))
        OutlinedTextField(
            value = input, onValueChange = {
                input = it
                //What should happen, when the value changes
            }, modifier = Modifier.align(Alignment.CenterHorizontally), textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Light
            ), label = { Text("Enter value") }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            val context = LocalContext.current
            Box {
                Button(
                    onClick = {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
                    first = true;
                }) {
                    Text("Select")
                    Icon(icon(first), contentDescription = "Localized description")
                }
                DropdownMenu(expanded = first, onDismissRequest = { first = false}) {
                    DropdownMenuItem(text = {Text("Hello")}, onClick = { /*TODO*/ })
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Box() {
                Button(onClick = {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
                    second = true;
                }) {
                    Text("Click me")
                    Icon(icon(second), contentDescription = "Localized description")
                }
                DropdownMenu(expanded = second, onDismissRequest = { second = false}) {
                    DropdownMenuItem(text = {Text("Hello")}, onClick = { /*TODO*/ })
                }


            }
        }
        Box() {
            Button(onClick = {
                when (textStyle.value) {
                    h1 -> textStyle.value = h2
                    h2 -> textStyle.value = h1
                };
            }) {
                Text("Change color")
            }
        }
    }
}

//Use for debug widget
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun GreetingPreview() {
    Widget();
}

