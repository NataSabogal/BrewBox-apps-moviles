package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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

@Composable
fun PlansScreen(onSelectPlan: () -> Unit) {

    var selectedPlan by remember { mutableStateOf(1) } // 0=Basic, 1=Explorer, 2=Barista

    val plans = listOf(
        PlanData(
            name = "Basic",
            emoji = "☕",
            tag = "ESSENTIAL",
            price = "\$15",
            period = "per month",
            description = "1 specialty coffee box per month",
            features = listOf("1 bag of single origin coffee", "Tasting notes card"),
            badge = null,
            badgeColor = null
        ),
        PlanData(
            name = "Explorer",
            emoji = "☕☕",
            tag = "JOURNEY",
            price = "\$28",
            period = "per month",
            description = "2 specialty coffee boxes per month",
            features = listOf(
                "2 bags from different origins",
                "Detailed tasting notes",
                "Brewing guide included"
            ),
            badge = "MOST POPULAR",
            badgeColor = Brown700
        ),
        PlanData(
            name = "Barista",
            emoji = "☕☕☕",
            tag = "PREMIUM",
            price = "\$52",
            period = "per month",
            description = "4 bags per month + accessories",
            features = listOf(
                "4 premium origin coffees",
                "Exclusive barista accessories",
                "Priority shipping",
                "Monthly brewing masterclass"
            ),
            badge = "BEST VALUE",
            badgeColor = Brown300
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Brown700)
                }
                Text(
                    text = "Choose your plan",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkBrown
                )
            }

            Text(
                text = "Select the coffee experience that fits your lifestyle.",
                fontSize = 13.sp,
                color = DarkBrown.copy(alpha = 0.5f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Plan cards
            plans.forEachIndexed { index, plan ->
                PlanCard(
                    plan = plan,
                    isSelected = selectedPlan == index,
                    onClick = { selectedPlan = index }
                )
                Spacer(Modifier.height(12.dp))
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("✅", fontSize = 16.sp)
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                "Quality Guaranteed",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = DarkBrown
                            )
                            Text(
                                "Top 1% of beans sourced from ethical farms worldwide.",
                                fontSize = 11.sp,
                                color = DarkBrown.copy(alpha = 0.5f)
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🚚", fontSize = 16.sp)
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Text(
                                "Free Express Shipping",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp,
                                color = DarkBrown
                            )
                            Text(
                                "Door-to-door delivery within 48 hours of processing.",
                                fontSize = 11.sp,
                                color = DarkBrown.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Cream)
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Button(
                onClick = onSelectPlan,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Brown700)
            ) {
                Text(
                    "START MY SUBSCRIPTION",
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    color = Cream
                )
            }
        }
    }
}

data class PlanData(
    val name: String,
    val emoji: String,
    val tag: String,
    val price: String,
    val period: String,
    val description: String,
    val features: List<String>,
    val badge: String?,
    val badgeColor: androidx.compose.ui.graphics.Color?
)

@Composable
fun PlanCard(
    plan: PlanData,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Brown700 else Brown300.copy(alpha = 0.4f),
                shape = RoundedCornerShape(16.dp)
            )
            .background(if (isSelected) Brown700.copy(alpha = 0.05f) else Color.White)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(plan.emoji, fontSize = 14.sp)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = plan.tag,
                            fontSize = 10.sp,
                            color = Brown700.copy(alpha = 0.6f),
                            letterSpacing = 1.sp
                        )
                    }
                    Text(
                        text = plan.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = DarkBrown
                    )
                    Text(
                        text = plan.description,
                        fontSize = 12.sp,
                        color = DarkBrown.copy(alpha = 0.5f)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    if (plan.badge != null) {
                        Box(
                            modifier = Modifier
                                .background(
                                    plan.badgeColor ?: Brown700,
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                plan.badge,
                                fontSize = 9.sp,
                                color = Cream,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                    }
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = plan.price,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Brown700
                        )
                    }
                    Text(
                        text = plan.period,
                        fontSize = 11.sp,
                        color = DarkBrown.copy(alpha = 0.4f)
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            plan.features.forEach { feature ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = if (isSelected) Brown700 else Brown300,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = feature,
                        fontSize = 12.sp,
                        color = DarkBrown.copy(alpha = 0.7f)
                    )
                }
                Spacer(Modifier.height(3.dp))
            }
        }

        // Checkmark seleccionado
        if (isSelected) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                    .background(Brown700, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                    tint = Cream,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}