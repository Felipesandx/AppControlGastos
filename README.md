# AppControlGastos - Core Domain 🧠⚙️

![Kotlin](https://img.shields.io/badge/Kotlin-Pure-purple.svg)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36.svg)
![Clean Architecture](https://img.shields.io/badge/Architecture-Clean-brightgreen.svg)

Este repositorio contiene el **núcleo puro** de la aplicación de control de gastos. Es un módulo desarrollado íntegramente en Kotlin y **completamente agnóstico a cualquier framework de interfaz de usuario** (como Android, Spring o KMP). 

Al seguir los principios de **Clean Architecture** (Arquitectura Limpia) y el patrón de puertos y adaptadores (Arquitectura Hexagonal), toda la lógica de negocio, reglas financieras y modelos residen aquí, haciéndolo altamente testeable, escalable y reutilizable.

## 💡 ¿Por qué esta separación?

1.  **Testing Ultrarrápido:** Al no depender del SDK de Android, las pruebas unitarias se ejecutan en la JVM en cuestión de milisegundos.
2.  **Reutilización Extrema:** Este mismo núcleo (`.jar`) podría conectarse en el futuro a una API REST (Spring Boot), a una aplicación web, o a una CLI en la terminal sin reescribir ni una sola línea de lógica de negocio.
3.  **Mantenibilidad:** Los cambios en la interfaz de usuario no afectan las reglas financieras, y los cambios en la base de datos (Room, SQL, Firebase) no afectan al dominio.

## 📂 Estructura del Dominio

El proyecto está organizado semánticamente por conceptos de negocio:

```text
src/main/kotlin/
├── data/
│   └── repository/
│       └── InMemoryTransactionRepository.kt # Implementación en memoria ideal para Testing
└── domain/
    ├── model/               
    │   ├── Category.kt      # Categorías de los gastos
    │   ├── MonthlySummary.kt # Modelo para el reporte mensual
    │   ├── Transaction.kt   # Entidad principal de transacciones
    │   ├── TransactionType.kt # Ingreso / Egreso
    │   └── UserProfile.kt   # Perfil del usuario
    ├── parser/              
    │   └── QuickAddParser.kt # Intérprete para añadir gastos por texto (Ej: "50 cena")
    ├── repository/          
    │   └── TransactionRepository.kt # Puerto/Contrato que el cliente (Android) debe implementar
    └── service/             
        └── FinanceService.kt # Lógica central: cálculos de balance, agrupación, etc.
```

## ✨ Funcionalidades Destacadas

*   **Reglas de Negocio Centralizadas:** `FinanceService` procesa todo el cálculo de balances y resúmenes sin depender de consultas SQL.
*   **Procesamiento de Lenguaje Natural (NLP Básico):** El `QuickAddParser` permite convertir entradas de texto libre en objetos `Transaction`, facilitando una experiencia de usuario más rápida e intuitiva.
*   **Testing Integrado:** Proporciona un `InMemoryTransactionRepository` para poder realizar pruebas de integración de los servicios sin necesidad de mockear repositorios o levantar bases de datos reales.

## 🛠 Requisitos y Construcción

Este proyecto utiliza **Maven** para la gestión de dependencias y empaquetado.

1.  **Requisitos:** Tener instalado Maven y JDK 17+.
2.  **Compilar y empaquetar:**
    En la raíz del proyecto, ejecuta:
    ```bash
    mvn clean compile
    ```
3.  **Instalar localmente:**
    Para usar este módulo en tu proyecto de Android, instálalo en tu repositorio Maven local:
    ```bash
    mvn clean install
    ```
    Luego, en tu `build.gradle.kts` de Android, podrás agregarlo como una dependencia.