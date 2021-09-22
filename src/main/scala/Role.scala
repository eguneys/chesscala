package chess

sealed trait Role:

  val forsyth: Char
  lazy val pgn: Char = forsyth.toUpper

end Role


case object Role:

  val all: List[Role] = List(King, Queen, Rook, Bishop, Knight, Pawn)

  val allByPgn: Map[Char, Role] = all.map{ p => (p.pgn -> p) }.toMap

end Role


case object King extends Role:
  val forsyth = 'k'
end King

case object Queen extends Role:
  val forsyth = 'q'
end Queen

case object Rook extends Role:
  val forsyth = 'r'
end Rook

case object Bishop extends Role:
  val forsyth = 'b'
end Bishop

case object Knight extends Role:
  val forsyth = 'n'
end Knight

case object Pawn extends Role:

  val forsyth = 'p'
end Pawn

