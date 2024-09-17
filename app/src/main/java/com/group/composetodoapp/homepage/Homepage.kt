package com.group.composetodoapp.homepage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Homepage() {
    Text(text = "Homepage")
}

@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    Homepage()
}