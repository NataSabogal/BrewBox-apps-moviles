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

@Composable
fun RegisterScreen(
    onRegister: (String, String, String, String, String) -> Unit, // Añadido password
    onBackToLogin: () -> Unit
) {

    // --- Estado de los campos ---
    var fullName    by remember { mutableStateOf("") }
    var birthday    by remember { mutableStateOf("") }
    var email       by remember { mutableStateOf("") }
    var address     by remember { mutableStateOf("") }
    var password    by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // --- Errores de validación ---
    var nameError     by remember { mutableStateOf(false) }
    var emailError    by remember { mutableStateOf(false) }
    var addressError  by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    // Colores del Design System
    val primaryBrown = MaterialTheme.colorScheme.primary       // #6F4E37
    val secondaryBrown = MaterialTheme.colorScheme.secondary   // #C8A882
    val cream = MaterialTheme.colorScheme.background           // #FDF6EC
    val darkBrown = MaterialTheme.colorScheme.onBackground     // #2C1A0E
    val errorRed = MaterialTheme.colorScheme.error             // #C62828

    fun validate(): Boolean {
        nameError     = fullName.isBlank()
        emailError    = email.isBlank() || !email.contains("@")
        addressError  = address.isBlank()
        passwordError = password.length < 6
        return !nameError && !emailError && !addressError && !passwordError
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(cream)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── Header ──────────────────────────────────────────────
            Spacer(Modifier.height(8.dp))

            // Ícono de taza (emoji temático, sin depender de recursos)
            Text(
                text = "☕",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Welcome to BrewBox",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = darkBrown,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Your daily specialty coffee journey starts here.",
                style = MaterialTheme.typography.bodyMedium,
                color = darkBrown.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // ── Tarjeta del formulario ───────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    // Full Name
                    Column {
                        Text(
                            "Full name",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = {
                                fullName = it
                                if (nameError) nameError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Fullname", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = nameError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed,
                                focusedLabelColor = primaryBrown
                            )
                        )
                        if (nameError) {
                            Text(
                                "❌ El nombre es obligatorio",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                            )
                        }
                    }

                    // Birthday
                    Column {
                        Text(
                            "Birthday",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = birthday,
                            onValueChange = { birthday = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Birthday", color = darkBrown.copy(alpha = 0.4f)) },
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown
                            )
                        )
                    }

                    // Email
                    Column {
                        Text(
                            "Email Address",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                if (emailError) emailError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("name@example.com", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = emailError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed
                            )
                        )
                        if (emailError) {
                            Text(
                                "❌ Ingresa un email válido",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                            )
                        }
                    }

                    // Address
                    Column {
                        Text(
                            "Address",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = address,
                            onValueChange = {
                                address = it
                                if (addressError) addressError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Enter your address", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = addressError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed
                            )
                        )
                        if (addressError) {
                            Text(
                                "❌ La dirección es obligatoria",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                            )
                        }
                    }

                    // Password
                    Column {
                        Text(
                            "Password",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                if (passwordError) passwordError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Enter your password", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = passwordError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            visualTransformation = if (passwordVisible)
                                VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible)
                                            Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null,
                                        tint = secondaryBrown
                                    )
                                }
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed
                            )
                        )
                        if (passwordError) {
                            Text(
                                "❌ La contraseña debe tener al menos 6 caracteres",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ── Botón Create ─────────────────────────────────────────
            Button(
                onClick = {
                    if (validate()) onRegister(email, fullName, address, birthday, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryBrown,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    "Create",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(16.dp))

            TextButton(
                onClick = onBackToLogin,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Already have an account? ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkBrown.copy(alpha = 0.6f)
                    )
                    Text(
                        "Sign In",
                        style = MaterialTheme.typography.bodyMedium,
                        color = primaryBrown,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}