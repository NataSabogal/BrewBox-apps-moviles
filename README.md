# BrewBox - Coffee Subscription App ☕

BrewBox es una aplicación móvil robusta desarrollada para el ecosistema Android moderno. El proyecto traduce un diseño de alta fidelidad en una experiencia funcional, priorizando la arquitectura declarativa, la navegación fluida y la persistencia de datos local.

## 👥 Integrantes del Grupo
*   **Integrante 1:** Natalia Sabogal Rada
*   **Integrante 2:** Samuel Valencia Baez


---

## 🚀 Especificaciones Técnicas

Para el desarrollo se han empleado las herramientas estándar de la industria:
- **IDE:** Android Studio Ladybug (2024.2.1)
- **Framework de UI:** Jetpack Compose (100% Declarativo)
- **Lenguaje:** Kotlin 2.2.10
- **Navegación:** Jetpack Navigation Component
- **Arquitectura:** MVVM (Model-View-ViewModel)
- **Persistencia Local:** Room Database & DataStore Preferences
- **Gestión de Dependencias:** Gradle (Kotlin DSL)

---

## 🛠️ Cumplimiento de Requisitos

### 1. Interfaz y Layouts (>80%)
- **Material Design 3:** Implementación de componentes `Scaffold`, `NavigationBar`, `Card` y `Button` con estética coherente.
- **Responsividad:** Uso de Modificadores y Layouts modernos (`Column`, `Row`, `Box`) para asegurar adaptabilidad.
- **Fidelidad:** Se ha respetado la paleta de colores y tipografías definidas en el prototipo original.

### 2. Navegación entre Vistas (>70%)
- **NavHost Centralizado:** Gestión de rutas a través de un `AppNavigation.kt`.
- **Flujos Complejos:** Implementación de un flujo de registro multinivel: 
  `Register` -> `Plans` -> `Payment` -> `Delivery` -> `Success`.
- **Bottom Navigation:** Navegación persistente entre las secciones Home, Catalog, My Box y Profile.
- **Gestión de Backstack:** Uso de `popUpTo` para evitar duplicidad de pantallas y asegurar un botón "Atrás" funcional.

### 3. Almacenamiento Local (>30%)
- **Room Database:** Persistencia de los perfiles de usuario y estados críticos.
- **DataStore:** Almacenamiento de preferencias y sesiones de usuario.
- **Gestión de Estado:** Uso de `StateFlow` y `collectAsState` para reflejar cambios en la base de datos en tiempo real en la UI.

---




   

## 📂 Estructura del Proyecto
