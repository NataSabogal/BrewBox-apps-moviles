package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.brewbox.ui.theme.*

@Composable
fun LoginScreen(
    onSignIn: (String) -> Unit,
    onCreateAccount: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))

        // Logo
        Text(text = "☕", fontSize = 40.sp)
        Spacer(Modifier.height(16.dp))

        Text(
            text = "Welcome to BrewBox",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkBrown,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Your daily specialty coffee journey starts here.",
            fontSize = 14.sp,
            color = DarkBrown.copy(alpha = 0.6f),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(28.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Brown300.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                .padding(4.dp)
        ) {
            listOf("Sign In", "Create Account").forEachIndexed { index, label ->
                Button(
                    onClick = {
                        if (index == 0) {
                            selectedTab = 0
                        } else {
                            onCreateAccount()
                        }
                    },
                    modifier = Modifier.weight(1f).height(40.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedTab == index) Brown700
                        else androidx.compose.ui.graphics.Color.Transparent,
                        contentColor = if (selectedTab == index) Cream else DarkBrown
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Text(label, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        if (selectedTab == 1) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full name") },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown700,
                    unfocusedBorderColor = Brown300
                )
            )
            Spacer(Modifier.height(12.dp))
        }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ""
            },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = RoundedCornerShape(12.dp),
            isError = emailError.isNotEmpty(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Brown700,
                unfocusedBorderColor = Brown300,
                errorBorderColor = ErrorRed
            )
        )
        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = ErrorRed,
                fontSize = 11.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 2.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility
                        else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Brown300
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            isError = passwordError.isNotEmpty(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Brown700,
                unfocusedBorderColor = Brown300,
                errorBorderColor = ErrorRed
            )
        )
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = ErrorRed,
                fontSize = 11.sp,
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp, top = 2.dp)
            )
        }

        if (selectedTab == 0) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                TextButton(onClick = {}) {
                    Text(
                        "Forgot password?",
                        fontSize = 12.sp,
                        color = Brown700
                    )
                }
            }
        } else {
            Spacer(Modifier.height(12.dp))
        }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                var valid = true
                if (email.isBlank()) { emailError = "Enter your email"; valid = false }
                if (password.isBlank()) { passwordError = "Enter your password"; valid = false }
                if (valid) {
                    if (selectedTab == 0) onSignIn(email) else onCreateAccount()
                }
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Brown700)
        ) {
            Text(
                text = if (selectedTab == 0) "Sign In" else "Create",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Cream
            )
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "OR CONTINUE WITH",
            fontSize = 11.sp,
            color = DarkBrown.copy(alpha = 0.4f),
            letterSpacing = 1.sp
        )

        Spacer(Modifier.height(48.dp))
    }
}