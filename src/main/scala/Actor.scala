package chess


case class Actor(piece: Piece,
  pos: Pos,
  board: Board):

  // pawn push
  // pawn push 2
  // pawn capture
  // pawn push promote
  // pawn capture promote
  // pawn en passant
  // king castle
  // king move
  // king capture
  // vision move
  // vision capture

end Actor

case class RayBoard(ray: Ray,
  board: Board):


  def actor: Option[Piece] = 
    board(ray.orig)

  def actee: Option[Piece] =
    ray.dest.flatMap(board.apply)


  def capture: Option[Piece] = for {
    a1 <- actor
    a2 <- actee
    if a1.color != a2.color
  } yield a2


  def pins: Map[Pos, Piece] = 
    ray.medium.flatMap(pos =>
        board(pos).map(pos -> _)).toMap

    
  def nopin: Boolean = pins.size == 0


  def singlepin: Option[(Pos, Piece)] = 
    pins.headOption.filter(_ => pins.size == 1)

  def absolutepin: Option[(Pos, Piece)] =
    singlepin.filter(_._2.king)
  

end RayBoard


// List[Ray] for a Pos Piece
// is a ray pinned?
// vacant orig
// List[Ray] for a board
// Ray discovered
//



case class VisionMove(before: Board,
  pos: Pos,
  piece: Piece):

  def after = ???

end VisionMove

case class PawnPush(before: Board, 
  pos: Pos,
  piece: Piece):

  def after = ???

end PawnPush

