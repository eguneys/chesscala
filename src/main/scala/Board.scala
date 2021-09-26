package chess

case class Board(pieces: Map[Pos, Piece]):


  lazy val rays: Map[Pos, List[RayBoard]] = pieces collect { 
    case (pos, Piece(color, role: RayRole)) =>
    pos -> role.rays(pos).map(RayBoard(_, this))
  }


  def apply(pos: Pos): Option[Piece] =
    pieces.get(pos)

  def pickup(pos: Pos): Option[Board] =
    if (pieces.contains(pos)) Some(copy(pieces = pieces - pos))
    else None

  def drop(pos: Pos, piece: Piece): Option[Board] =
    if (pieces.contains(pos)) None
    else Some(copy(pieces = pieces + (pos -> piece)))


  def empty(pos: Pos): Boolean =
    !pieces.contains(pos)

    
end Board
