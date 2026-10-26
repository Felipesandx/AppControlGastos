package umg.edu.gt.domain.model

data class Transaction(
    val id: Long,
    val userId: Long, // Nuevo campo
    val amount: Double,
    val description: String,
    val date: String,
    val timestamp: Long,
    val type: TransactionType,
    val category: Category,
    val isImpulse: Boolean
)