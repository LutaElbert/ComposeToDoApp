package com.group.composetodoapp.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ScaffoldComponent(modifier: Modifier = Modifier, content: @Composable (Modifier) -> Unit) {
    Scaffold{ paddingValues ->
        content.invoke(Modifier.padding(paddingValues))
    }
}