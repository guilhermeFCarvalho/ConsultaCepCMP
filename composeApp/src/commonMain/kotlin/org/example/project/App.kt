package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import org.example.project.cep_feature.presentation.screens.AddressScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AddressScreen()
    }
}