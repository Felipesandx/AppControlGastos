package umg.edu.gt.domain.repository
import umg.edu.gt.domain.model.Transaction

interface TransactionRepository {
    suspend fun insert(transaction: Transaction): Transaction
    suspend fun update(transaction: Transaction): Transaction
    suspend fun delete(id: Long): Boolean
    suspend fun getAll(userId: Long): List<Transaction> // Agregado userId
    suspend fun deleteAll(userId: Long) // Agregado userId
}