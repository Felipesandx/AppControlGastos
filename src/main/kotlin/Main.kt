package umg.edu.gt

import kotlinx.coroutines.runBlocking
import umg.edu.gt.data.repository.InMemoryTransactionRepository
import umg.edu.gt.domain.model.Category
import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.model.TransactionType
import umg.edu.gt.domain.service.FinanceService

fun main() = runBlocking {
    val service = FinanceService(InMemoryTransactionRepository())

    println("--- Test de Librería de Finanzas ---")

    service.register(
        Transaction(
            amount = 3500.0,
            description = "Pago de Quincena",
            date = "2026-10-15",
            timestamp = System.currentTimeMillis(),
            type = TransactionType.INCOME,
            category = Category.SALARY
        )
    )

    service.register(
        Transaction(
            amount = 45.0,
            description = "Almuerzo",
            date = "2026-10-15",
            timestamp = System.currentTimeMillis(),
            type = TransactionType.EXPENSE,
            category = Category.FOOD
        )
    )

    val summary = service.getMonthlySummary()
    println("Ingresos: " + service.formatCurrency(summary.totalIncome))
    println("Gastos:   " + service.formatCurrency(summary.totalExpenses)) // Con 's' al final
    println("Balance:  " + service.formatCurrency(summary.netBalance))
}