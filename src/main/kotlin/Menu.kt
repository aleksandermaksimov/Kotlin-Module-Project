import java.util.Scanner


class Menu (private val menuItems: List<String>) {
    private val scanner = Scanner(System.`in`)

    fun displayMenu() {
        for ((index, item) in menuItems.withIndex()) {
            println("$index. $item")
        }
    }

    fun getUserChoice(prompt: String): Int {
        var choice: Int
        do {
            println(prompt)
            val input = scanner.nextLine()
            choice = input.toIntOrNull() ?: -1
            if (choice !in 0 until menuItems.size) {
                println("Пожалуйста, введите корректный номер пункта меню.")
            }
        } while (choice !in 0 until menuItems.size)
        return choice
    }
}


