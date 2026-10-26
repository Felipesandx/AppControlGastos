package umg.edu.gt.domain.repository

import umg.edu.gt.domain.model.Transaction

interface TransactionRepository {
    suspend fun insert(transaction: Transaction): Transaction
    suspend fun getAll(): List<Transaction>
    suspend fun getById(id: Long): Transaction?
    suspend fun update(transaction: Transaction): Boolean
    suspend fun delete(id: Long): Boolean
    suspend fun deleteAll()
}