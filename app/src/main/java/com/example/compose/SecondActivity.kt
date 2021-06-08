package com.example.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
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
        Text(text = "Hai" , Modifier.firstBaselineToTop(55.dp))
        Text(text = "Jeee" , Modifier.padding(top = 55.dp))
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

    @Preview
    @Composable
    fun DefaultPreview() {
        MyApp {
            MySecondScreenLayout()
        }
    }
}