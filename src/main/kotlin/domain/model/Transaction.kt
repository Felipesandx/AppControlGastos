package umg.edu.gt.domain.model

enum class TransactionType { EXPENSE, INCOME }

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val description: String,
    val date: String, //Para formato yyyy-MM-dd
    val timestamp: Long, // Para ordenar calculos de rango
    val type: TransactionType,
    val category: Category = Category.OTHER,
    val isImpulse: Boolean = false
)