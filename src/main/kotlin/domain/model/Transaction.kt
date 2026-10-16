package umg.edu.gt.domain.model

enum class TransactionType { EXPENSE, INCOME }

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val description: String,
    val timestamp: Long,
    val type: TransactionType,
    val categoryId: Long? = null,
    val isImpulse: Boolean = false // Diferenciador: compras por impulso
)