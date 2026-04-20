package com.example.brewbox.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeliveryScreen(onConfirm: () -> Unit) {

    // ── Estado de campos ──────────────────────────────────────────────
    var fullName      by remember { mutableStateOf("") }
    var streetAddress by remember { mutableStateOf("") }
    var aptSuite      by remember { mutableStateOf("") }
    var city          by remember { mutableStateOf("") }
    var state         by remember { mutableStateOf("") }
    var postalCode    by remember { mutableStateOf("") }
    var country       by remember { mutableStateOf("United States") }
    var deliveryNotes by remember { mutableStateOf("") }

    // ── Errores ───────────────────────────────────────────────────────
    var nameError   by remember { mutableStateOf(false) }
    var streetError by remember { mutableStateOf(false) }
    var cityError   by remember { mutableStateOf(false) }
    var postalError by remember { mutableStateOf(false) }

    // ── Colores del Design System ─────────────────────────────────────
    val primaryBrown   = MaterialTheme.colorScheme.primary      // #6F4E37
    val secondaryBrown = MaterialTheme.colorScheme.secondary    // #C8A882
    val cream          = MaterialTheme.colorScheme.background   // #FDF6EC
    val darkBrown      = MaterialTheme.colorScheme.onBackground // #2C1A0E
    val errorRed       = MaterialTheme.colorScheme.error        // #C62828
    val surface        = MaterialTheme.colorScheme.surface      // #FFFFFF

    // ── Colores de los campos (pill style, fondo blanco, borde gris claro) ──
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor        = primaryBrown,
        unfocusedBorderColor      = Color(0xFFDDD5CC),
        errorBorderColor          = errorRed,
        unfocusedContainerColor   = Color.White,
        focusedContainerColor     = Color.White,
        errorContainerColor       = Color.White,
        unfocusedPlaceholderColor = Color(0xFFB8ADA5),
        focusedPlaceholderColor   = Color(0xFFB8ADA5),
        unfocusedTextColor        = darkBrown,
        focusedTextColor          = darkBrown,
    )

    fun validate(): Boolean {
        nameError   = fullName.isBlank()
        streetError = streetAddress.isBlank()
        cityError   = city.isBlank()
        postalError = postalCode.isBlank()
        return !nameError && !streetError && !cityError && !postalError
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

            // ════════════════════════════════════════════════════════
            // TOP BAR — mismo estilo que PaymentScreen
            // ════════════════════════════════════════════════════════
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
                    Text(
                        text = "← Delivery address",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = darkBrown,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Step 3 of 3",
                        style = MaterialTheme.typography.labelSmall,
                        color = darkBrown.copy(alpha = 0.5f),
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            }

            // ════════════════════════════════════════════════════════
            // PROGRESS BAR — mismo estilo que PaymentScreen
            // ════════════════════════════════════════════════════════
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "FINALIZING SETUP",
                        style = MaterialTheme.typography.labelSmall,
                        color = darkBrown.copy(alpha = 0.5f)
                    )
                    Text(
                        "100%",
                        style = MaterialTheme.typography.labelSmall,
                        color = primaryBrown,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { 1f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = primaryBrown,
                    trackColor = secondaryBrown.copy(alpha = 0.3f)
                )
            }

            // ════════════════════════════════════════════════════════
            // FORMULARIO — fondo blanco, campos pill, labels encima
            // ════════════════════════════════════════════════════════
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                // ── Full name ────────────────────────────────────────
                DeliveryFieldLabel("Full name")
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it; if (nameError) nameError = false },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("John Doe") },
                    isError = nameError,
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),       // pill shape
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                    colors = fieldColors
                )
                if (nameError) DeliveryErrorText("El nombre es obligatorio")
                Spacer(Modifier.height(8.dp))

                // ── Street address ───────────────────────────────────
                DeliveryFieldLabel("Street address")
                OutlinedTextField(
                    value = streetAddress,
                    onValueChange = { streetAddress = it; if (streetError) streetError = false },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("123 Coffee Lane") },
                    isError = streetError,
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = fieldColors
                )
                if (streetError) DeliveryErrorText("La dirección es obligatoria")
                Spacer(Modifier.height(8.dp))

                // ── Apt/Suite (Optional) ─────────────────────────────
                DeliveryFieldLabel("Apt/Suite (Optional)")
                OutlinedTextField(
                    value = aptSuite,
                    onValueChange = { aptSuite = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Unit 4B") },
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = fieldColors
                )
                Spacer(Modifier.height(8.dp))

                // ── City + State ─────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1.4f)) {
                        DeliveryFieldLabel("City")
                        OutlinedTextField(
                            value = city,
                            onValueChange = { city = it; if (cityError) cityError = false },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("Seattle") },
                            isError = cityError,
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = fieldColors
                        )
                        if (cityError) DeliveryErrorText("Obligatorio")
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        DeliveryFieldLabel("State")
                        OutlinedTextField(
                            value = state,
                            onValueChange = { if (it.length <= 3) state = it.uppercase() },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("WA") },
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            colors = fieldColors
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))

                // ── Postal code + Country ────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        DeliveryFieldLabel("Postal code")
                        OutlinedTextField(
                            value = postalCode,
                            onValueChange = {
                                if (it.length <= 10) {
                                    postalCode = it.filter { c -> c.isDigit() }
                                    if (postalError) postalError = false
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text("98101") },
                            isError = postalError,
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = fieldColors
                        )
                        if (postalError) DeliveryErrorText("Obligatorio")
                    }
                    Column(modifier = Modifier.weight(1.4f)) {
                        DeliveryFieldLabel("Country")
                        // Dropdown simulado con ícono de flecha
                        OutlinedTextField(
                            value = country,
                            onValueChange = { country = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            shape = RoundedCornerShape(50.dp),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = darkBrown.copy(alpha = 0.5f),
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            colors = fieldColors
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))

                // ── Delivery instructions ────────────────────────────
                DeliveryFieldLabel("Delivery instructions")
                OutlinedTextField(
                    value = deliveryNotes,
                    onValueChange = { deliveryNotes = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    placeholder = { Text("Gate code, where to leave package, etc.") },
                    maxLines = 4,
                    // Campo multilinea: esquinas más redondeadas pero no pill total
                    shape = RoundedCornerShape(20.dp),
                    colors = fieldColors
                )
            }

            Spacer(Modifier.height(16.dp))

            // ════════════════════════════════════════════════════════
            // CARD INFO — Free shipping + Estimated arrival
            // ════════════════════════════════════════════════════════
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("🚚", fontSize = 16.sp)
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Free shipping on all BrewBox plans",
                            style = MaterialTheme.typography.bodySmall,
                            color = darkBrown
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("📅", fontSize = 16.sp)
                        Spacer(Modifier.width(10.dp))
                        Text(
                            "Estimated arrival: Oct 12 – Oct 15",
                            style = MaterialTheme.typography.bodySmall,
                            color = darkBrown
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // ════════════════════════════════════════════════════════
            // BOTÓN — negro oscuro, igual al screenshot del Figma
            // ════════════════════════════════════════════════════════
            Button(
                onClick = { if (validate()) onConfirm() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A1A2E),   // negro oscuro del Figma
                    contentColor   = Color.White
                )
            ) {
                Text(
                    "Start my BrewBox journey",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

// ── Composables auxiliares ────────────────────────────────────────────────────

@Composable
private fun DeliveryFieldLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
    )
}

@Composable
private fun DeliveryErrorText(message: String) {
    Text(
        text = "❌ $message",
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(top = 2.dp, start = 8.dp)
    )
}