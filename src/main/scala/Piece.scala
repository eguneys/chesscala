package chess

case class Piece(color: Color,
  role: Role):
 
  def king: Boolean = role == King
  def queen: Boolean = role == Queen 
end Piece


case object Piece:

  def fromChar(c: Char): Option[Piece] =
    Role.allByPgn get c.toUpper map {
      Piece(Color.fromWhite(c.isUpper), _)
    }
  


end Piece
