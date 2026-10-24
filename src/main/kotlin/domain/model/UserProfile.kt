package umg.edu.gt.domain.model

data class UserProfile(
    val name: String,
    val monthlyBudget: Double,
    val isDiscreetModeEnabled: Boolean = false
)