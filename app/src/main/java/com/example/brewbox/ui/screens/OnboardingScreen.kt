package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewbox.ui.theme.*

data class OnboardingStep(
    val stepLabel: String,
    val question: String,
    val subtitle: String,
    val options: List<String>,
    val icon: String
)

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {

    val steps = listOf(
        OnboardingStep(
            stepLabel = "STEP 1 OF 3",
            question = "How do you take your coffee?",
            subtitle = "Help us personalize your monthly box.",
            options = listOf("Black", "With milk", "Depends on my mood"),
            icon = "☕"
        ),
        OnboardingStep(
            stepLabel = "STEP 2 OF 3",
            question = "What roast do you prefer?",
            subtitle = "We'll curate your box based on your taste.",
            options = listOf("Light Roast", "Medium Roast", "Dark Roast"),
            icon = "🫘"
        ),
        OnboardingStep(
            stepLabel = "STEP 3 OF 3",
            question = "How often do you brew?",
            subtitle = "This helps us size your monthly selection.",
            options = listOf("Once a day", "Multiple times a day", "A few times a week"),
            icon = "📅"
        )
    )

    var currentStep by remember { mutableStateOf(0) }
    var selectedOption by remember { mutableStateOf(0) }

    val step = steps[currentStep]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(Modifier.height(16.dp))

            // ── Top bar: dots + skip ──────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Progress dots
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    steps.indices.forEach { index ->
                        val isActive = index == currentStep
                        Box(
                            modifier = Modifier
                                .height(8.dp)
                                .width(if (isActive) 28.dp else 8.dp)
                                .clip(CircleShape)
                                .background(if (isActive) Brown700 else Brown300.copy(alpha = 0.4f))
                        )
                    }
                }

                TextButton(onClick = onContinue) {
                    Text(
                        text = "Skip",
                        color = Brown700,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(Modifier.height(32.dp))

            // ── Step label ───────────────────────────────────────────
            Text(
                text = step.stepLabel,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Brown700,
                letterSpacing = 1.sp
            )

            Spacer(Modifier.height(8.dp))

            // ── Question ─────────────────────────────────────────────
            Text(
                text = step.question,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = DarkBrown,
                lineHeight = 36.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = step.subtitle,
                fontSize = 14.sp,
                color = DarkBrown.copy(alpha = 0.5f)
            )

            Spacer(Modifier.height(28.dp))

            // ── Options ──────────────────────────────────────────────
            step.options.forEachIndexed { index, option ->
                val isSelected = selectedOption == index

                Surface(
                    onClick = { selectedOption = index },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .border(
                            width = if (isSelected) 1.5.dp else 1.dp,
                            color = if (isSelected) Brown700 else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    color = if (isSelected) Brown700.copy(alpha = 0.08f) else Color.White,
                    shadowElevation = if (isSelected) 0.dp else 1.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option,
                            fontSize = 15.sp,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                            color = DarkBrown
                        )

                        // Radio circle
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Brown700 else Color.Transparent)
                                .border(
                                    width = 1.5.dp,
                                    color = if (isSelected) Brown700 else Brown300,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(Cream)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.weight(1f))

            // ── Decorative icon ──────────────────────────────────────
            Text(
                text = step.icon,
                fontSize = 64.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Brown300.copy(alpha = 0.4f)
            )

            Spacer(Modifier.weight(1f))
        }

        // ── Continue button (fixed at bottom) ────────────────────────
        Button(
            onClick = {
                if (currentStep < steps.lastIndex) {
                    currentStep++
                    selectedOption = 0
                } else {
                    onContinue()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .height(56.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Brown700)
        ) {
            Text(
                text = "Continue →",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Cream
            )
        }
    }
}