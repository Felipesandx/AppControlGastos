package umg.edu.gt.domain.service

import umg.edu.gt.domain.model.MonthlySummary
import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.model.TransactionType
import umg.edu.gt.domain.repository.TransactionRepository
import java.util.Locale

class FinanceService(private val repository: TransactionRepository) {

    suspend fun register(transaction: Transaction): String? {
        val error = validate(transaction)
        if (error != null) return error
        repository.insert(transaction)
        return null
    }

    suspend fun update(transaction: Transaction): String? {
        val error = validate(transaction)
        if (error != null) return error
        val updated = repository.update(transaction)
        if (!updated) return "No existe el registro con ID #${transaction.id}"
        return null
    }

    suspend fun listTransactions(): List<Transaction> =
        repository.getAll().reversed().sortedByDescending { it.timestamp }

    suspend fun getById(id: Long): Transaction? = repository.getById(id)

    suspend fun delete(id: Long): Boolean = repository.delete(id)

    suspend fun getMonthlySummary(): MonthlySummary {
        val transactions = repository.getAll()
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        val impulse = transactions.filter { it.type == TransactionType.EXPENSE && it.isImpulse }.sumOf { it.amount }

        return MonthlySummary(
            totalIncome = income,
            totalExpenses = expense,
            netBalance = income - expense,
            impulseExpensesTotal = impulse
        )
    }

    private fun validate(t: Transaction): String? {
        if (t.description.isBlank()) return "La descripción no puede estar vacía"
        if (t.amount <= 0.0) return "El monto debe ser mayor a 0"
        return null
    }

    fun formatCurrency(amount: Double): String =
        String.format(Locale.US, "Q %.2f", amount)
}