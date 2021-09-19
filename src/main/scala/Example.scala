package example

// 
// rook check king
// 
// moves 
// 
// mated
// 
// no check on king
// 
// moves
// 
// stalemate
// 
// no check on king
// 
// moves
// give check
// 
// rook discovers check on king
// rook cuts off flight squares
// 
// moves
// rook cut off flee
// 
//
//  before 
//  Vc3->e5
//  after
//  -Vc3->e5
//  +Ve5->e4
// 
//
// 
// Vision -> Vision
// 
// capture -> to source
// interpose -> to medium 
// discover -> from medium
// flee -> from source
// 
case class Pos()

case class Medium(
  from: Pos,
  source: Piece,
  to: Pos,
  dest: Option[Piece],
  medium: List[(Pos, Option[Piece])]):

  def color = source.color


  def blocks: List[Piece] = medium.flatMap(_._2)

end Medium

case class Vision(
  medium: Medium):


  // medium is empty
  def drop(vision: Vision): Vision =
    ???

  def capture(vision: Vision): Option[Vision] = ???

  def apply(vision: Vision): Vision = 
    ???

end Vision

case class Orb(all: List[Vision],
  turn: Color):

  lazy val allTurn: List[Vision] = all.filter(v => v.medium.color == turn)

  lazy val one: List[Orb] = allTurn
    .map(v =>
        Orb(all.flatMap(
          _.capture(v)
            .map(_.apply(v))), turn))


end Orb

case object Orb {
  
  def make(pieces: Map[Pos, Piece], turn: Color): Orb =
    val all = (pieces.flatMap {
      case (pos, piece) =>
        piece.visions(pos)
    }).foldLeft(List[Vision]()) { (acc, v) =>
        v :: acc.map(_.drop(v))
    }
    Orb(all, turn)
}


