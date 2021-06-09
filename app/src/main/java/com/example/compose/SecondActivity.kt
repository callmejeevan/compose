package com.example.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MySecondScreenLayout()
            }
        }
    }

    @Composable
    fun MySecondScreenLayout(){
        val context = LocalContext.current
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "SecondOne")
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }
                    },
                )
            }
        ) {
            MySecondScreenContent()
        }
    }

    @Composable
    fun MySecondScreenContent(){
        Column {
            Text(text = "Hai" , Modifier.firstBaselineToTop(55.dp))
            Text(text = "Jeee" , Modifier.padding(top = 55.dp))
            MyOwnColumn(modifier = Modifier.background(Color.Yellow)) {
                Text(text = "Jeevan", modifier = Modifier.background(Color.Green))
                Text(text = "Venugopal")
            }
        }
        
    }

    // Sample custom modifier from code lab
    fun Modifier.firstBaselineToTop(
        firstBaselineToTop: Dp
    ) = this.then(
        layout { measurable, constraints ->
            val placeable = measurable.measure(constraints)

            // Check the composable has a first baseline
            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
            val firstBaseline = placeable[FirstBaseline]


            // Height of the composable with padding - first baseline
            val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
            val height = placeable.height + placeableY
            layout(placeable.width, height) {
                // Where the composable gets placed
                placeable.placeRelative(0, placeableY)
            }
        }
    )
    
    // Sample custom layout from the code lab
    @Composable
    fun MyOwnColumn(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        Layout(
            modifier = modifier,
            content = content
        ) { measurable, constraints ->

            // Don't constrain child views further, measure them with given constraints
            // List of measured children
            val placeables = measurable.map { measurable ->
                // Measure each child
                measurable.measure(constraints)
            }

            // Track the y co-ord we have placed children up to
            var yPosition = 0

            // Set the size of the layout as big as it can
            layout(constraints.maxWidth, constraints.maxHeight) {
                // Place children in the parent layout
                placeables.forEach { placeable ->
                    // Position item on the screen
                    placeable.placeRelative(x = 0, y = yPosition)

                    // Record the y co-ord placed up to
                    yPosition += placeable.height
                }
            }
        }
    }



    @Preview
    @Composable
    fun DefaultPreview() {
        MyApp {
            MySecondScreenLayout()
        }
    }
}