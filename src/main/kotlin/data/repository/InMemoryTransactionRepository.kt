package umg.edu.gt.data.repository

import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.repository.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {
    private val transactions = mutableListOf<Transaction>()
    private var currentId: Long = 1

    override suspend fun insert(transaction: Transaction): Transaction {
        val newTransaction = transaction.copy(id = currentId++)
        transactions.add(newTransaction)
        return newTransaction
    }

    override suspend fun getAll(): List<Transaction> = transactions.toList()

    override suspend fun getById(id: Long): Transaction? =
        transactions.find { it.id == id }

    override suspend fun update(transaction: Transaction): Boolean {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        if (index == -1) return false
        transactions[index] = transaction
        return true
    }

    override suspend fun delete(id: Long): Boolean =
        transactions.removeIf { it.id == id }

    override suspend fun deleteAll() {
        transactions.clear()
    }
}
