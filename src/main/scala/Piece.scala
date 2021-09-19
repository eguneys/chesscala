package example

sealed trait Piece:

  val color: Color
  val role: Role

  def visions(pos: Pos): List[Vision] = 
    ???

end Piece

case object BlackKing extends Piece:

  val color = Black
  val role = King
end BlackKing 



case object WhiteKing extends Piece:
  val color = White
  val role = King
end WhiteKing 

