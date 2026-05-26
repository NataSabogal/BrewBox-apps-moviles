package com.example.brewbox.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.brewbox.notifications.NotificationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ShippingStatus(val step: Int, val label: String) {
    ROASTED(1, "CAFÉ TOSTADO"),
    SHIPPED(2, "EN CAMINO"),
    NEARBY(3, "CERCA DE TU UBICACIÓN"),
    DELIVERED(4, "ENTREGADO")
}

class BoxViewModel(application: Application) : AndroidViewModel(application) {
    private val notificationHelper = NotificationHelper(application)
    
    private val _shippingStatus = MutableStateFlow(ShippingStatus.SHIPPED)
    val shippingStatus: StateFlow<ShippingStatus> = _shippingStatus.asStateFlow()

    fun updateStatus(newStatus: ShippingStatus) {
        _shippingStatus.value = newStatus
        sendStatusNotification(newStatus)
    }

    private fun sendStatusNotification(status: ShippingStatus) {
        val (title, message) = when (status) {
            ShippingStatus.ROASTED -> "Tu café ha sido tostado" to "El maestro tostador ha terminado tu lote. ¡Huele de maravilla!"
            ShippingStatus.SHIPPED -> "Tu BrewBox está en camino" to "Tu pedido ha salido de nuestras instalaciones."
            ShippingStatus.NEARBY -> "¡Ya casi llega!" to "El repartidor está en tu zona. Ten tu taza lista."
            ShippingStatus.DELIVERED -> "¡Entregado!" to "Tu BrewBox ya debería estar en tu puerta. ¡Disfrútalo!"
        }
        notificationHelper.showNotification(title, message)
    }
}
