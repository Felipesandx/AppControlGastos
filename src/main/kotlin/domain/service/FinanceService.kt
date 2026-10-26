package umg.edu.gt.domain.service

import umg.edu.gt.domain.model.MonthlySummary
import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.model.TransactionType
import umg.edu.gt.domain.model.UserProfile
import umg.edu.gt.domain.repository.TransactionRepository
import java.text.NumberFormat
import java.util.Locale

class FinanceService(private val repository: TransactionRepository) {

    suspend fun register(transaction: Transaction): String? {
        if (transaction.amount <= 0) return "El monto debe ser mayor a 0"
        if (transaction.description.isBlank()) return "La descripción no puede estar vacía"
        repository.insert(transaction)
        return null
    }

    suspend fun update(transaction: Transaction): String? {
        if (transaction.amount <= 0) return "El monto debe ser mayor a 0"
        if (transaction.description.isBlank()) return "La descripción no puede estar vacía"
        repository.update(transaction)
        return null
    }

    suspend fun delete(id: Long): Boolean {
        return repository.delete(id)
    }

    // Modificado
    suspend fun listTransactions(userId: Long): List<Transaction> {
        return repository.getAll(userId).sortedByDescending { it.timestamp }
    }

    // Modificado
    suspend fun getMonthlySummary(userId: Long): MonthlySummary {
        val all = listTransactions(userId)
        val incomes = all.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expenses = all.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        val impulses = all.filter { it.type == TransactionType.EXPENSE && it.isImpulse }.sumOf { it.amount }

        return MonthlySummary(
            totalIncome = incomes,
            totalExpenses = expenses,
            netBalance = incomes - expenses,
            impulseExpensesTotal = impulses
        )
    }

    // Modificado
    suspend fun wipeAllData(userId: Long) {
        repository.deleteAll(userId)
    }

    fun calculateDailySafeToSpend(profile: UserProfile, totalExpensesThisMonth: Double, daysRemainingInMonth: Int): Double {
        val baseBudget = 3000.0
        val remainingBudget = baseBudget - totalExpensesThisMonth
        if (remainingBudget <= 0 || daysRemainingInMonth <= 0) return 0.0
        return remainingBudget / daysRemainingInMonth
    }

    fun formatCurrency(amount: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "GT"))
        return format.format(amount).replace("GTQ", "Q").trim()
    }
}