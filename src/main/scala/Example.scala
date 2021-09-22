//package example

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
//
// 
//
//
//
//
//
//
/*
 *
 *
case class Pos()

sealed trait EscapeFail:
end EscapeFail

case class Medium(
  from: Pos,
  source: Piece,
  to: Pos,
  dest: Option[Piece],
  medium: List[(Pos, Option[Piece])]):

  def color = source.color


  def blocks: List[Piece] = medium.flatMap(_._2)


  def direct: Boolean = blocks.isEmpty

end Medium

case class Vision(
  medium: Medium):


  // medium is empty
  def drop(vision: Vision): Vision =
    ???

  def escape(vision: Vision): Boolean = ???

  def capture(vision: Vision): Option[Vision] = ???

  def apply(vision: Vision): Vision = 
    ???  
end Vision


IamMated
IMated
Capture Not IamMated
CaptureBlocks IamMated
PinnedCantCapture IamMated
PinnedCantGiveCheck Not IMated
DiscoveredCheck IMated


case class Orb(all: List[Vision],
  turn: Color):

  // king moves
  // look for safe squares
  //
  // checkable moves
  // look for expose discovery
  // queen giving check
  // 
  // all moves
  // king moves
  // checkable moves
  //
  // capture moves
  // interpose moves
  // giving check moves
  //
  //

  // all safe moves
  // look for opposing check
  // if there is one 
  // escape result
  // for the check
  // capture
  // interpose
  // king flee
  // mated result
  // otherwise
  // all moves result

  // move application
  //
  // one move ahead
  // all safe moves 
  // apply
  // remove captured
  // apply changes


  lazy val moves = List[Move]


end Orb

case object Orb {
  
  def make(pieces: Map[Pos, Piece], turn: Color): Orb = ???
}

*/
