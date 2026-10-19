package umg.edu.gt.domain.model

enum class Category (val displayName: String){
    FOOD("Alimentacion"),
    TRANSPORT("Transporte"),
    STUDIES("Estudios"),
    HEALTH("Salud"),
    ENTERTAINMENT("Ocio"),
    SALARY("Salario / Ingreso"),
    OTHER("Otros");

    override fun toString(): String = displayName

}