package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.brewbox.data.UserEntity

@Composable
fun ProfileScreen(
    userData: UserEntity? = null,
    onBack: () -> Unit = {},
    onSignOut: () -> Unit = {}
) {
    var fullName by remember(userData) { mutableStateOf(userData?.fullName ?: "James Brewington") }
    var email by remember(userData) { mutableStateOf(userData?.email ?: "james.b@example.com") }
    var phone by remember { mutableStateOf("+1 (555) 000-1234") }
    var address by remember(userData) { mutableStateOf(userData?.address ?: "123 Espresso Lane") }
    var city by remember { mutableStateOf("Seattle") }
    var zip by remember { mutableStateOf("98101") }

    val preferences = remember { mutableStateListOf("Strong", "Chocolatey", "Arabica") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkBrown
                )
            }
            Text(
                text = "Personal Info",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            TextButton(onClick = {}) {
                Text(
                    text = "Save",
                    color = Brown700,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(Brown300.copy(alpha = 0.4f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "👤", fontSize = 40.sp)
                }
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Brown700),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "📷", fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Edit Photo",
                color = Brown700,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileField(label = "Full name", value = fullName, onValueChange = { fullName = it })
            ProfileField(label = "Email", value = email, onValueChange = { email = it })
            ProfileField(label = "Phone", value = phone, onValueChange = { phone = it })
        }

        Spacer(Modifier.height(24.dp))

        // Coffee preferences
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "My coffee preferences",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                preferences.forEach { pref ->
                    val isSelected = pref != "Arabica"
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isSelected) Brown700 else Color.Transparent,
                        modifier = Modifier.border(
                            1.dp,
                            if (isSelected) Brown700 else Brown300,
                            RoundedCornerShape(20.dp)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = pref,
                                fontSize = 13.sp,
                                color = if (isSelected) Cream else DarkBrown,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "×",
                                fontSize = 13.sp,
                                color = if (isSelected) Cream.copy(alpha = 0.7f) else DarkBrown.copy(alpha = 0.5f)
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color.Transparent,
                modifier = Modifier.border(1.dp, Brown300, RoundedCornerShape(20.dp))
            ) {
                Text(
                    text = "+ Add preference",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    color = DarkBrown.copy(alpha = 0.6f),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Delivery address",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown700,
                    unfocusedBorderColor = Brown300
                )
            )
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Brown700,
                        unfocusedBorderColor = Brown300
                    )
                )
                OutlinedTextField(
                    value = zip,
                    onValueChange = { zip = it },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Brown700,
                        unfocusedBorderColor = Brown300
                    )
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "DANGER ZONE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ErrorRed,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(8.dp))
            OutlinedButton(
                onClick = onSignOut,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRed),
                border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRed.copy(alpha = 0.4f))
            ) {
                Text(text = "↪ Sign out", fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(8.dp))
            TextButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Delete account",
                    color = ErrorRed.copy(alpha = 0.6f),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(Modifier.height(32.dp))
    }
}

@Composable
fun ProfileField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkBrown,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Brown700,
                unfocusedBorderColor = Brown300
            )
        )
    }
}