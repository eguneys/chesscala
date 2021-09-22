package chess

sealed trait Color:

  def apply(role: Role): Piece =
    Piece(this, role)

  def unary_! : Color

end Color

case object Color:

  def apply(char: Char): Option[Color] = char match {
    case 'w' => Some(White)
    case 'b' => Some(Black)
    case _ => None
  }


  def fromWhite(b: => Boolean) = b match {
    case true => White
    case false => Black
  }

end Color

case object White extends Color:
  def unary_! = Black
end White
case object Black extends Color:
  def unary_! = White
end Black


