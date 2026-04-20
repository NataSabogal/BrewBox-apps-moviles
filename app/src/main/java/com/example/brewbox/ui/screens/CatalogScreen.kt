package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewbox.ui.theme.*

data class CoffeeItem(
    val id: Int,
    val name: String,
    val roast: String,
    val notes: String,
    val origin: String,
    val originCode: String,
    val isLocked: Boolean = false,
    val unlockDate: String? = null
)

@Composable
fun CatalogScreen(onCoffeeDetail: (Int) -> Unit = {}) {
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Colombia", "Ethiopia", "Brazil", "Kenya")

    val coffees = listOf(
        CoffeeItem(1, "Huila Reserve", "Medium Roast", "Caramel, Red Apple", "Colombia", "COL"),
        CoffeeItem(2, "Yirgacheffe G1", "Light Roast", "Floral, Jasmine, Lemon", "Ethiopia", "ETH"),
        CoffeeItem(3, "Santos Gold", "Dark Roast", "Chocolate, Roasted Nut", "Brazil", "BRA"),
        CoffeeItem(4, "Nyeri Hill", "Medium Roast", "Coming Soon", "Kenya", "KEN", isLocked = true, unlockDate = "Unlocks Nov 1st"),
        CoffeeItem(5, "Sumatra Mandheling", "Dark Roast", "Coming Soon", "Indonesia", "IDN", isLocked = true),
        CoffeeItem(6, "Tarrazú", "Light Roast", "Coming Soon", "Costa Rica", "CRI", isLocked = true)
    )

    val filteredCoffees = if (selectedFilter == "All") coffees
    else coffees.filter { it.origin == selectedFilter }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Brown300)
            )

            Text(
                text = "Catalog",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = DarkBrown
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                val isSelected = selectedFilter == filter
                Surface(
                    onClick = { selectedFilter = filter },
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) Brown700 else Color.Transparent,
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = if (isSelected) Brown700 else Brown300,
                        shape = RoundedCornerShape(20.dp)
                    )
                ) {
                    Text(
                        text = filter,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = if (isSelected) Cream else DarkBrown,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredCoffees) { coffee ->
                CoffeeCard(coffee = coffee, onClick = { onCoffeeDetail(coffee.id) })
            }
        }
    }
}

@Composable
fun CoffeeCard(coffee: CoffeeItem, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Brown300.copy(alpha = 0.3f))
        ) {
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopStart),
                shape = RoundedCornerShape(6.dp),
                color = DarkBrown.copy(alpha = 0.75f)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(text = "🇨🇴", fontSize = 10.sp)
                    Text(
                        text = coffee.originCode,
                        color = Cream,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (coffee.isLocked) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBrown.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🔒", fontSize = 28.sp)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = coffee.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = DarkBrown
        )
        Text(
            text = if (coffee.isLocked && coffee.unlockDate != null) coffee.unlockDate else coffee.roast,
            fontSize = 12.sp,
            color = if (coffee.isLocked && coffee.unlockDate != null) Brown700 else Brown700,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = if (coffee.isLocked) "Coming Soon" else coffee.notes,
            fontSize = 11.sp,
            color = DarkBrown.copy(alpha = 0.5f)
        )
    }
}