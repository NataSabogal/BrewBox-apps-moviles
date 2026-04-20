package com.example.brewbox.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val email: String,
    val fullName: String,
    val address: String,
    val birthday: String,
    val profileImage: String? = null,
    val subscriptionPlan: String? = null
)
