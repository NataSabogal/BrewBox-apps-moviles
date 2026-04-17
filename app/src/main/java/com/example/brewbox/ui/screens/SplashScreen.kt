package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewbox.ui.theme.Brown700
import com.example.brewbox.ui.theme.Cream
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onContinue: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1500)
        onContinue()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brown700),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "☕",
                fontSize = 72.sp
            )
            Spacer(Modifier.height(24.dp))
            Text(
                text = "BrewBox",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Cream
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "YOUR MONTHLY COFFEE JOURNEY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Cream.copy(alpha = 0.7f),
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}