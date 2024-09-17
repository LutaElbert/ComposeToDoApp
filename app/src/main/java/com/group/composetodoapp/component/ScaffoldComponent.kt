package com.group.composetodoapp.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScaffoldComponent(modifier: Modifier, content: @Composable (Modifier) -> Unit) {
    Scaffold(
        modifier = modifier.padding(8.dp),
    ) { paddingValues ->
        content.invoke(Modifier.padding(paddingValues))
    }
}