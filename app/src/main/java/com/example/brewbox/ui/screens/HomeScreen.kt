package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewbox.ui.theme.*

@Composable
fun HomeScreen(
    userName: String? = null,
    onTrackOrder: () -> Unit = {},
    onSeeHistory: () -> Unit = {},
    onCoffeeDetail: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Good morning, ${userName?.split(" ")?.firstOrNull() ?: "Coffee Lover"} ",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                    Text(text = "☕", fontSize = 18.sp)
                }
                Text(
                    text = "Gold Member",
                    fontSize = 12.sp,
                    color = DarkBrown.copy(alpha = 0.5f)
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Brown300.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = DarkBrown,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(Brown300.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = "☕",
                        fontSize = 72.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )

                    Surface(
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopStart),
                        shape = RoundedCornerShape(20.dp),
                        color = DarkBrown.copy(alpha = 0.85f)
                    ) {
                        Text(
                            text = "IN TRANSIT",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Cream,
                            letterSpacing = 1.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "MARCH SELECTION",
                        fontSize = 11.sp,
                        color = Brown700,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "Your Monthly Box",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )

                    Spacer(Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Arriving Thursday",
                            fontSize = 13.sp,
                            color = DarkBrown.copy(alpha = 0.7f)
                        )
                        Text(
                            text = "75%",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown
                        )
                    }

                    Spacer(Modifier.height(6.dp))

                    LinearProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = Brown700,
                        trackColor = Brown300.copy(alpha = 0.3f)
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "Currently in: Memphis Hub",
                        fontSize = 11.sp,
                        color = DarkBrown.copy(alpha = 0.4f)
                    )

                    Spacer(Modifier.height(14.dp))

                    // Bottom row: arrives + button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Arrives in 3 days",
                            fontSize = 13.sp,
                            color = DarkBrown.copy(alpha = 0.6f)
                        )
                        Button(
                            onClick = onTrackOrder,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Brown700)
                        ) {
                            Text(
                                text = "Track Order",
                                color = Cream,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            listOf(
                Triple("📦", "12", "BOXES"),
                Triple("☕", "48", "COFFEES"),
                Triple("🏆", "Pro", "PLAN")
            ).forEach { (icon, value, label) ->
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = icon, fontSize = 18.sp)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = value,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = DarkBrown
                        )
                        Text(
                            text = label,
                            fontSize = 10.sp,
                            color = DarkBrown.copy(alpha = 0.4f),
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Your last coffee",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            TextButton(onClick = onSeeHistory) {
                Text(
                    text = "SEE HISTORY",
                    fontSize = 11.sp,
                    color = Brown700,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable { onCoffeeDetail() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Row(
                modifier = Modifier.padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Brown300.copy(alpha = 0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("☕", fontSize = 26.sp)
                }

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "SINGLE ORIGIN",
                        fontSize = 10.sp,
                        color = Brown700,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Yirgacheffe G1",
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Floral, Jasmine, Lemon",
                        fontSize = 12.sp,
                        color = DarkBrown.copy(alpha = 0.5f)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Cream),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "›", fontSize = 20.sp, color = DarkBrown)
                }
            }
        }

        Spacer(Modifier.height(14.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable { onCoffeeDetail() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Brown700.copy(alpha = 0.08f)),
            elevation = CardDefaults.cardElevation(0.dp)
        ) {
            Row(modifier = Modifier.padding(14.dp)) {
                Text("💡", fontSize = 18.sp)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Brewing Tip",
                        fontWeight = FontWeight.SemiBold,
                        color = Brown700,
                        fontSize = 13.sp
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = "For your Ethiopia Yirgacheffe, try a slightly coarser grind and a 1:16 ratio for maximum floral clarity.",
                        fontSize = 12.sp,
                        color = DarkBrown.copy(alpha = 0.7f),
                        lineHeight = 18.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(28.dp))
    }
}