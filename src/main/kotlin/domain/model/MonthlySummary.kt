package umg.edu.gt.domain.model

data class MonthlySummary(
    val totalIncome: Double,
    val totalExpenses: Double,            // <-- Termina en 's' (plural)
    val netBalance: Double,
    val impulseExpensesTotal: Double       // <-- O impulseExpenseTotal, según corresponda
)