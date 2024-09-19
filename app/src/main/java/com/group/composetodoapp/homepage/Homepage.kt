package com.group.composetodoapp.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group.composetodoapp.component.NavigationDrawerComponent
import com.group.composetodoapp.component.ScaffoldComponent
import kotlinx.coroutines.launch

@Composable
fun Homepage() {
    ScaffoldComponent { modifier ->
        MainContent(modifier)
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        NavigationDrawerComponent(drawerState) {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                SearchBox(
                    onToggleClicked = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SearchBox(modifier: Modifier = Modifier, onToggleClicked: () -> Unit) {
    val fieldValue = rememberSaveable {
        mutableStateOf("")
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onToggleClicked.invoke()
                    },
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu"
            )

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp,end = 8.dp),
                value = fieldValue.value,
                onValueChange = {
                    fieldValue.value = it
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    Homepage()
}