package umg.edu.gt.data.repository

import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.repository.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {
    private val transactions = mutableListOf<Transaction>()
    private var nextId = 1L

    override suspend fun insert(transaction: Transaction): Transaction {
        val newTransaction = transaction.copy(id = nextId++)
        transactions.add(newTransaction)
        return newTransaction
    }

    override suspend fun update(transaction: Transaction): Transaction {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        if (index != -1) {
            transactions[index] = transaction
        }
        return transaction
    }

    override suspend fun delete(id: Long): Boolean {
        return transactions.removeIf { it.id == id }
    }

    override suspend fun getAll(userId: Long): List<Transaction> {
        return transactions.filter { it.userId == userId }
    }

    override suspend fun deleteAll(userId: Long) {
        transactions.removeIf { it.userId == userId }
    }
}