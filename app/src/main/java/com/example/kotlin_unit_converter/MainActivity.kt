package com.example.kotlin_unit_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
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

fun icon(expand: Boolean): ImageVector {
    if (expand) return Icons.Filled.KeyboardArrowDown
    return Icons.Filled.KeyboardArrowLeft
}

@Composable
fun Widget() {

    val textStyle = remember {
        mutableStateOf(h1);
    }
    var input = remember {
        mutableStateOf(" ");
    }
    var first by remember {
        mutableStateOf(false);
    }
    var second by remember {
        mutableStateOf(false);
    }
    var from by remember {
        mutableStateOf(converterFunction.m);
    }
    var to by remember {
        mutableStateOf(converterFunction.km);
    }
    var output = remember {
        mutableStateOf(" ");
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter", style = textStyle.value)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = androidx.compose.ui.text.input.ImeAction.Default,
                ), singleLine = true, value = input.value, onValueChange = {
                    input.value = it
                    converter(it, from, to, output)
                }, textStyle = TextStyle(
                    color = Color.Black, fontWeight = FontWeight.Light
                ), label = { Text("Enter a number") });
                Box() {
                    Button(onClick = {
                        first = true;
                    }) {
                        Text(from.toString())
                        Icon(icon(first), contentDescription = "Localized description")
                    }
                    DropdownMenu(expanded = first, onDismissRequest = { first = false }) {
                        for (test: converterFunction in converterFunction.entries) {
                            DropdownMenuItem(text = { Text(test.name) },
                                onClick = { from = test;first = false })
                        }
                    }
                }

            }
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .weight(0.1f)
                    .fillMaxSize()
            ) {
                Text(
                    text = "=",
                    modifier = Modifier.padding(bottom = 20.dp),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                )
            }
            //output
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = androidx.compose.ui.text.input.ImeAction.Default,
                ), value = output.value, singleLine = true, onValueChange = {
                    output.value = it
                    converter(it, from, to, input)
                }, textStyle = TextStyle(
                    color = Color.Black, fontWeight = FontWeight.Light
                ), label = { Text("Converter") });
                Box() {
                    Button(onClick = {
                        second = true;
                    }) {
                        Text(to.toString())
                        Icon(icon(second), contentDescription = "Localized description")
                    }
                    DropdownMenu(expanded = second, onDismissRequest = { second = false }) {
                        for (test: converterFunction in converterFunction.entries) {
                            DropdownMenuItem(text = { Text(test.name) },
                                onClick = { to = test;second = false })
                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Box() {
            Button(onClick = {
                val value = input.value.toDoubleOrNull()
                if (value != null) {
                    val result = converterFunction.convert(value, from, to)
                    output.value = result.toString()
                }
            }) {
                Text("Convert")
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


private fun converter(
    it: String, from: converterFunction, to: converterFunction, output: MutableState<String>
) {
    try {
        val input = it.toDouble()
        output.value = converterFunction.convert(input, from, to).toString()
    } catch (e: NumberFormatException) {
        // Handle invalid numeric input
        output.value = " "
        println(e)// Or any other appropriate message
        // You might also want to log the exception for debugging purposes
    }
}

//Use for debug widget
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun GreetingPreview() {
    Widget();
}

