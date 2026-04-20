package com.example.brewbox.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScreen(onBack: () -> Unit) {
    val primaryBrown = Color(0xFF6F4E37)
    val cream = Color(0xFFFDF6EC)
    val darkBrown = Color(0xFF2C1A0E)
    val lightBrown = Color(0xFFC8A882)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "BrewBox",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(cream)
        ) {
            // Placeholder for the "Camera Preview" / Coffee Bag Image
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                // Background coffee bag simulation (using a colored box as a placeholder)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.7f)
                        .clip(RoundedCornerShape(24.dp))
                        .background(lightBrown.copy(alpha = 0.3f))
                )

                // Viewfinder Overlay
                ScannerOverlay(
                    modifier = Modifier
                        .size(280.dp),
                    color = primaryBrown
                )
            }

            // UI Elements
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                // Instruction text
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Align the QR code on your BrewBox to scan",
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkBrown,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Action Buttons (Flashlight & Gallery)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    IconButton(
                        onClick = { /* Toggle Flashlight */ },
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(primaryBrown)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FlashlightOn,
                            contentDescription = "Flashlight",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { /* Open Gallery */ },
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(primaryBrown)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Gallery",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Cancel Button
                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(56.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        "Cancel",
                        color = primaryBrown,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ScannerOverlay(modifier: Modifier = Modifier, color: Color) {
    Canvas(modifier = modifier) {
        val strokeWidth = 8.dp.toPx()
        val cornerLength = 40.dp.toPx()
        val halfStroke = strokeWidth / 2

        // Top Left Corner
        drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(halfStroke, halfStroke),
            size = Size(cornerLength, cornerLength),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Top Right Corner
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(size.width - cornerLength - halfStroke, halfStroke),
            size = Size(cornerLength, cornerLength),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Bottom Left Corner
        drawArc(
            color = color,
            startAngle = 90f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(halfStroke, size.height - cornerLength - halfStroke),
            size = Size(cornerLength, cornerLength),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Bottom Right Corner
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(size.width - cornerLength - halfStroke, size.height - cornerLength - halfStroke),
            size = Size(cornerLength, cornerLength),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )

        // Horizontal Scanning Line
        drawLine(
            color = Color.Gray.copy(alpha = 0.5f),
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 2.dp.toPx()
        )
    }
}
