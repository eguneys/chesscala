package chess

case class Move(situationBefore: Situation,
  after: Board,
  gen: MoveGen):

  def color = situationBefore.color

  def before: Board = situationBefore.board
  def situationAfter = Situation(after, !color)

end Move

sealed trait MoveGen:

  val board: Situation

  def after: Option[Board]

  def move: Option[Move] = after map { Move(board, _, this) }


end MoveGen

case class Vision(
  board: Board,
  orig: Pos,
  dest: Pos,
  ray: Ray
  ) extends MoveGen:

  def medium: List[Pos] = 
    ray.cast(orig, dest)

  def after = for {
    b2 <- board.pickup(orig)
    if medium.every(board.empty _)
    b3 <- b2.drop(dest)
  } yield b3

end Vision
