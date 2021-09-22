package chess

class PerftTest extends ChessTest:

  def perft(sit: Situation, depth: Int): Int = {
    if (depth > 0)
      sit.moves.foldLeft(0) { (p, move) =>
        p + perft(sit(move), depth - 1)
      }
    else 1
  }

  "calculate standard chess perfts" >> {
    "gentest-1364" >> {
      val game = Situation("8/4k3/2r1Br2/Pp4Q1/3N4/1p5K/1b6/8 b - -").get 

      perft(game, 3) must be_==(18802)
    }
  }

end PerftTest
