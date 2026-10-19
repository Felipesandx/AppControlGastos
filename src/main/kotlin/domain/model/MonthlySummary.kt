package umg.edu.gt.domain.model

data class MonthlySummary (
    val totalIncome: Double,
    val totalExpenses: Double,
    val netBalance: Double,
    val impulseExpenseTotal: Double
)