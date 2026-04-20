package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PaymentScreen(onBack: () -> Unit = {}, onConfirm: () -> Unit) {

    // --- Estado de campos ---
    var cardholderName by remember { mutableStateOf("") }
    var cardNumber     by remember { mutableStateOf("") }
    var expiryDate     by remember { mutableStateOf("") }
    var cvv            by remember { mutableStateOf("") }

    // --- Errores ---
    var nameError   by remember { mutableStateOf(false) }
    var cardError   by remember { mutableStateOf(false) }
    var expiryError by remember { mutableStateOf(false) }
    var cvvError    by remember { mutableStateOf(false) }

    val primaryBrown   = MaterialTheme.colorScheme.primary
    val secondaryBrown = MaterialTheme.colorScheme.secondary
    val cream          = MaterialTheme.colorScheme.background
    val darkBrown      = MaterialTheme.colorScheme.onBackground
    val errorRed       = MaterialTheme.colorScheme.error
    val surface        = MaterialTheme.colorScheme.surface

    // Formatea el número de tarjeta en grupos de 4
    fun formatCardNumber(input: String): String {
        val digits = input.filter { it.isDigit() }.take(16)
        return digits.chunked(4).joinToString(" ")
    }

    // Formatea MM/YY
    fun formatExpiry(input: String): String {
        val digits = input.filter { it.isDigit() }.take(4)
        return if (digits.length > 2) digits.substring(0, 2) + "/" + digits.substring(2)
        else digits
    }

    fun validate(): Boolean {
        val rawCard = cardNumber.filter { it.isDigit() }
        nameError   = cardholderName.isBlank()
        cardError   = rawCard.length < 16
        expiryError = expiryDate.length < 5
        cvvError    = cvv.length < 3
        return !nameError && !cardError && !expiryError && !cvvError
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
        ) {

            // ── Top Bar ───────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(surface)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = darkBrown
                        )
                    }
                    Text(
                        text = "Payment",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = darkBrown,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "STEP 2/3",
                        style = MaterialTheme.typography.labelSmall,
                        color = darkBrown.copy(alpha = 0.5f),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }

            // ── Progress bar ─────────────────────────────────────────
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "SUBSCRIPTION PROGRESS",
                        style = MaterialTheme.typography.labelSmall,
                        color = darkBrown.copy(alpha = 0.5f)
                    )
                    Text(
                        "66%",
                        style = MaterialTheme.typography.labelSmall,
                        color = primaryBrown,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { 0.66f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = primaryBrown,
                    trackColor = secondaryBrown.copy(alpha = 0.3f)
                )
            }

            // ── Resumen de plan ───────────────────────────────────────
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = primaryBrown.copy(alpha = 0.08f)),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("📦", fontSize = 28.sp)
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            "Premium Brew Box",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = darkBrown
                        )
                        Text(
                            "Monthly Subscription",
                            style = MaterialTheme.typography.bodySmall,
                            color = darkBrown.copy(alpha = 0.6f)
                        )
                        Text(
                            "\$29.00 / mo",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = primaryBrown
                        )
                    }
                }
            }

            // ── Formulario de pago ────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    "Payment Details",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = darkBrown
                )

                // Cardholder Name
                Column {
                    Text(
                        "Cardholder Name",
                        style = MaterialTheme.typography.labelMedium,
                        color = darkBrown,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = cardholderName,
                        onValueChange = {
                            cardholderName = it
                            if (nameError) nameError = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Full Name", color = darkBrown.copy(alpha = 0.4f)) },
                        isError = nameError,
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBrown,
                            unfocusedBorderColor = secondaryBrown,
                            errorBorderColor = errorRed
                        )
                    )
                    if (nameError) {
                        Text(
                            "❌ Ingresa el nombre del titular",
                            color = errorRed,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                        )
                    }
                }

                // Card Number
                Column {
                    Text(
                        "Card Number",
                        style = MaterialTheme.typography.labelMedium,
                        color = darkBrown,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = {
                            val formatted = formatCardNumber(it)
                            cardNumber = formatted
                            if (cardError) cardError = false
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("0000 0000 0000 0000", color = darkBrown.copy(alpha = 0.4f)) },
                        isError = cardError,
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = {
                            Text("💳", fontSize = 20.sp, modifier = Modifier.padding(end = 8.dp))
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryBrown,
                            unfocusedBorderColor = secondaryBrown,
                            errorBorderColor = errorRed
                        )
                    )
                    if (cardError) {
                        Text(
                            "❌ Ingresa un número de tarjeta válido (16 dígitos)",
                            color = errorRed,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 2.dp, start = 4.dp)
                        )
                    }
                }

                // Expiry + CVV en fila
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Expiry Date
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Expiry Date",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = expiryDate,
                            onValueChange = {
                                expiryDate = formatExpiry(it)
                                if (expiryError) expiryError = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("MM/YY", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = expiryError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed
                            )
                        )
                        if (expiryError) {
                            Text(
                                "❌ Fecha inválida",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }

                    // CVV
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "CVV",
                            style = MaterialTheme.typography.labelMedium,
                            color = darkBrown,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        OutlinedTextField(
                            value = cvv,
                            onValueChange = {
                                if (it.length <= 4) {
                                    cvv = it.filter { c -> c.isDigit() }
                                    if (cvvError) cvvError = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("•••", color = darkBrown.copy(alpha = 0.4f)) },
                            isError = cvvError,
                            singleLine = true,
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            trailingIcon = {
                                Icon(
                                    Icons.Default.Lock,
                                    contentDescription = null,
                                    tint = secondaryBrown,
                                    modifier = Modifier.size(18.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = primaryBrown,
                                unfocusedBorderColor = secondaryBrown,
                                errorBorderColor = errorRed
                            )
                        )
                        if (cvvError) {
                            Text(
                                "❌ CVV inválido",
                                color = errorRed,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }
                    }
                }

                // ── Order Summary ─────────────────────────────────────
                Spacer(Modifier.height(8.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = surface),
                    elevation = CardDefaults.cardElevation(1.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            "Order Summary",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = darkBrown
                        )
                        HorizontalDivider(color = secondaryBrown.copy(alpha = 0.3f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Premium Subscription",
                                style = MaterialTheme.typography.bodyMedium,
                                color = darkBrown
                            )
                            Text(
                                "\$29.00",
                                style = MaterialTheme.typography.bodyMedium,
                                color = darkBrown,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Shipping",
                                style = MaterialTheme.typography.bodyMedium,
                                color = darkBrown
                            )
                            Text(
                                "Free",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        HorizontalDivider(color = secondaryBrown.copy(alpha = 0.3f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Total due today",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = darkBrown
                            )
                            Text(
                                "\$29.00",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = darkBrown
                            )
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                // ── Botón Confirmar ───────────────────────────────────
                Button(
                    onClick = {
                        if (validate()) onConfirm()
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
                    Icon(Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Confirm Subscription 🔒",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(Modifier.height(24.dp))
            }
        }
    }
}