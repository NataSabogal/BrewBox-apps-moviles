package com.example.brewbox.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoffeeDetailScreen(onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    val darkBrown = Color(0xFF2C1A0E)
    val primaryBrown = Color(0xFF6F4E37)
    val lightCream = Color(0xFFFDF6EC)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // --- Header with Image and Overlay ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            // Background Image placeholder (using a colored box with a gradient)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = 400f
                        )
                    )
            ) {
                // Here we would normally use an Image component
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(primaryBrown.copy(alpha = 0.5f)) // Placeholder color
                )
            }

            // Top Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.3f))
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }

            // Title and Origin
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Surface(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "ORIGIN HIGHLIGHT",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ethiopia\nYirgacheffe",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp
                )
            }

            // Favorite Button
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 24.dp, bottom = 24.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(4.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(primaryBrown),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }

        // --- Content Section ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            // Chips / Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DetailChip(icon = "🔥", label = "Light Roast")
                DetailChip(icon = "🌸", label = "Floral & Citrus")
                DetailChip(icon = "💧", label = "", isIconOnly = true)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Profile Notes
            Text(
                text = "Profile Notes",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = darkBrown
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Sourced from the high-altitude Gedeo Zone, this Yirgacheffe offers a tea-like body with distinct notes of bergamot and jasmine. Perfect for manual brewing methods that highlight its vibrant acidity.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Audio/Barista Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Barista Avatar Placeholder
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(lightCream),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👨🏽‍🍳", fontSize = 32.sp)
                    }
                    
                    Spacer(modifier = Modifier.width(16.dp))
                    
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Listen to the barista",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = darkBrown
                        )
                        Text(
                            text = "Brew guide & tasting tips • 2:45",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Waveform simulation
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            repeat(8) { index ->
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .height((10..24).random().dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(primaryBrown)
                                )
                            }
                        }
                    }

                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play",
                        tint = primaryBrown,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Cup Characteristics
            Text(
                text = "Cup Characteristics",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = darkBrown
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            CharacteristicBar("Acidity", 0.8f, "High")
            CharacteristicBar("Body", 0.3f, "Light")
            CharacteristicBar("Sweetness", 0.6f, "Medium")

            Spacer(modifier = Modifier.height(40.dp))

            // Footer Details (Altitude, Variety)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoCard(modifier = Modifier.weight(1f), label = "ALTITUDE", value = "1,800 - 2,200m")
                InfoCard(modifier = Modifier.weight(1f), label = "VARIETY", value = "Heirloom")
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun DetailChip(icon: String, label: String, isIconOnly: Boolean = false) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(horizontal = if (isIconOnly) 12.dp else 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 16.sp)
            if (!isIconOnly) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = label, style = MaterialTheme.typography.bodyMedium, color = Color(0xFF2C1A0E))
            }
        }
    }
}

@Composable
fun CharacteristicBar(label: String, progress: Float, level: String) {
    val primaryBrown = Color(0xFF6F4E37)
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
            Text(text = level, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = primaryBrown)
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = primaryBrown,
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}

@Composable
fun InfoCard(modifier: Modifier = Modifier, label: String, value: String) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFF7F9FB)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.Gray, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, color = Color(0xFF2C1A0E))
        }
    }
}
