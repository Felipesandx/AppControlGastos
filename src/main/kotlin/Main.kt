import kotlinx.coroutines.runBlocking
import umg.edu.gt.data.repository.InMemoryTransactionRepository
import umg.edu.gt.domain.model.Category
import umg.edu.gt.domain.model.Transaction
import umg.edu.gt.domain.model.TransactionType
import umg.edu.gt.domain.service.FinanceService

fun main() = runBlocking {
    val repository = InMemoryTransactionRepository()
    val service = FinanceService(repository)

    // Simulam un usuario en sesión
    val activeUserId = 1L

    val t1 = Transaction(
        id = 0,
        userId = activeUserId, // Nuevo campo
        amount = 25.0,
        description = "Café",
        date = "25 Oct 2026",
        timestamp = System.currentTimeMillis(),
        type = TransactionType.EXPENSE,
        category = Category.FOOD,
        isImpulse = true // Nuevo campo
    )

    service.register(t1)

    // Llamada al servicio pasando el usuario
    val summary = service.getMonthlySummary(activeUserId)

    println("Balance neto del usuario $activeUserId: Q ${summary.netBalance}")
}