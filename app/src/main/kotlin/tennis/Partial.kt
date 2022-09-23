package tennis

interface Partial {
    fun accept(player1Points: Int, player2Points: Int): Boolean
    fun format(player1Points: Int, player2Points: Int): String
    infix fun andThen(p2: Partial) = Partial.andThen(this, p2)

    companion object {
        fun andThen(partial1: Partial, partial2: Partial): Partial = object: Partial {
            override fun accept(player1Points: Int, player2Points: Int): Boolean {
                return partial1.accept(player1Points, player2Points) || partial2.accept(player1Points, player2Points)
            }

            override fun format(player1Points: Int, player2Points: Int): String {
                if(partial1.accept(player1Points, player2Points)) return partial1.format(player1Points, player2Points)
                if(partial2.accept(player1Points, player2Points)) return partial2.format(player1Points, player2Points)
                throw Exception("Param not accepted")
            }
        }

        fun pure(accept: (Int, Int) -> Boolean, format: (Int, Int) -> String): Partial = object: Partial {
            override fun accept(player1Points: Int, player2Points: Int): Boolean = accept(player1Points, player2Points)
            override fun format(player1Points: Int, player2Points: Int): String = format(player1Points, player2Points)
        }
        fun pure(accept: (Int, Int) -> Boolean, res: String): Partial = pure(accept) { _, _ -> res }
    }
}