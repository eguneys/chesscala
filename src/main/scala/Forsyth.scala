package chess

case object Forsyth:


  // "8/4k3/2r1Br2/Pp4Q1/3N4/1p5K/1b6/8 b - -"
  def <>(fen: String): Option[Situation] = {
    val splitted = fen split ' '
    splitted.lift(0) flatMap makeBoard flatMap { board =>
      val colorOption = splitted lift 1 flatMap (_ lift 0) flatMap Color.apply


      colorOption map { color =>
        Situation(board, color)
      }
    }
  }


  def makeBoard(fen: String): Option[Board] = makePieces(fen.toList, 1, 8) map { res => Board(res.toMap) }
   

  def makePieces(chars: List[Char],
    x: Int,

    y: Int): Option[List[(Pos, Piece)]] = 
      chars match {
        case Nil => Option(Nil)
        case '/' :: rest => 
          makePieces(rest, 1, y - 1)
        case c :: rest if '1' <= c && c <= '8' => 
          makePieces(rest, x + (c - '0').toInt, y)
        case c :: rest =>
          for {
            pos <- Pos.at(x, y)
            piece <- Piece.fromChar(c)
            nextPieces <- makePieces(rest, x + 1, y)

          } yield pos -> piece :: nextPieces
      }

end Forsyth
