package cinema

fun main() {


    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsTotal = readln().toInt()

    val allSeats = initSeats(rows, seatsTotal)

    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
    var opt = readln().toInt()
    var currIncome = 0
    var ticketCount = 0
    var totalSeatCount = rows * seatsTotal

    var totalIncome = if (totalSeatCount <= 60) {
        totalSeatCount * 10
    } else {
        (rows / 2) * 10 * seatsTotal + (rows - (rows / 2)) * 8 * seatsTotal
    }

    while (opt != 0) {
        if (opt == 1) {
            printAll(allSeats)
        } else if (opt == 2) {

            var valid = false

            while (!valid) {
                println()
                println("Enter a row number:")
                val row = readln().toInt()
                println("Enter a seat number in that row:")
                val seatNo = readln().toInt()

                if (row > allSeats.size || seatNo > allSeats[0].size) {
                    println()
                    println("Wrong input!")
                    valid = false
                } else if (allSeats[row - 1][seatNo - 1] == 'B') {
                    println("That ticket has already been purchased!")
                    valid = false
                } else {
                    valid = true
                }

                if (valid) {
                    if (rows * seatsTotal <= 60) {
                        currIncome += 10
                        println("Ticket price: \$10")
                    } else {
                        if (row <= rows / 2) {
                            currIncome += 10
                            println("Ticket price: \$10")
                        } else {
                            currIncome += 8
                            println("Ticket price: \$8")
                        }
                    }
                    ticketCount++
                    allSeats[row - 1][seatNo - 1] = 'B'
                }


            }

        } else if (opt == 3) {
            println("Number of purchased tickets: $ticketCount")
            val percentage = "%.2f".format((ticketCount.toDouble() / totalSeatCount.toDouble()) * 100)
            println("Percentage: ${percentage}%")
            println("Current income: $$currIncome")
            println("Total income: $$totalIncome")
        }

        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        opt = readln().toInt()
    }
}

fun initSeats(rows: Int, seatsTotal: Int): MutableList<MutableList<Char>> {
    val allSeats = mutableListOf<MutableList<Char>>()
    for (row in 0 until rows) {
        val seats = mutableListOf<Char>()
        for (seat in 0 until seatsTotal) {
            seats.add('S')
        }
        allSeats.add(seats)
    }

    return allSeats
}

fun printAll(list: MutableList<MutableList<Char>>) {
    var seats: String = ""
    for (i in 1..list[0].size) {
        if (i < list[0].size) {
            seats += "$i "
        } else {
            seats += "$i"
        }

    }
    println("Cinema:")
    println("  $seats")
    for (row in list.indices) {
        println("${row + 1} " + list[row].joinToString(" "))
    }
}