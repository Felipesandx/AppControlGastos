package umg.edu.gt.domain.model

data class UserProfile(
    val id: Long = 0,
    val name: String,
    val isDiscreetModeEnabled: Boolean = false
)