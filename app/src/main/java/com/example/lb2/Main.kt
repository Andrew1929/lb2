class InvalidMenuChoiceException(message: String) : Exception(message)

fun main() {
    val menu = mapOf(
        1 to Pair("Pizza", 150),
        2 to Pair("Burger", 120),
        3 to Pair("Sushi", 200),
        4 to Pair("Pasta", 130),
        5 to Pair("Salad", 80)
    )

    var total = 0
    var choice: Int

    println("Welcome to the Food Delivery Menu!")

    do {
        println("\nMenu:")
        for ((key, value) in menu) {
            val (dish, price) = value
            println("$key. $dish - $price UAH")
        }
        println("0. Exit and finish order")

        print("Enter your choice (0–5): ")
        val input = readlnOrNull()

        try {
            choice = input?.toIntOrNull() ?: throw InvalidMenuChoiceException("Invalid input format")

            if (choice == 0) break

            val item = menu[choice] ?: throw InvalidMenuChoiceException("Menu item $choice does not exist")
            total += item.second
            println("✅ Added '${item.first}' to your order. Current total: $total UAH")

        } catch (e: InvalidMenuChoiceException) {
            println("⚠️ Error: ${e.message}")
        } catch (e: Exception) {
            println("⚠️ Unexpected error: ${e.message}")
        }

    } while (true)

    println("\nFinal total before discount: $total UAH")

    val discount = when {
        total >= 500 -> 0.15
        total >= 200 -> 0.10
        else -> 0.0
    }

    val discountedTotal = total - (total * discount)
    val discountPercent = (discount * 100).toInt()

    if (discount > 0) {
        println("🎉 You got a $discountPercent% discount!")
    } else {
        println("No discount applied.")
    }

    println("💰 Total to pay: ${"%.2f".format(discountedTotal)} UAH")
}
