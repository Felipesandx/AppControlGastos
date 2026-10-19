package umg.edu.gt.domain.repository

import umg.edu.gt.domain.model.Transaction

interface TransactionRepository {
    fun insert(transaction: Transaction): Long
    fun getAll(): List<Transaction>
    fun getByDateRange(startTimestamp: Long, endTimestamp: Long): List<Transaction>
    fun delete(id: Long): Boolean
    fun getBalanceSummary(startTimestamp: Long, endTimestamp: Long): Pair<Double, Double>
}