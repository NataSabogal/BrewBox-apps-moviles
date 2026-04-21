package com.example.brewbox.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val email: String,
    val fullName: String,
    val address: String,
    val birthday: String,
    val password: String, // Añadimos el campo password
    val profileImage: String? = null,
    val subscriptionPlan: String? = null
)
