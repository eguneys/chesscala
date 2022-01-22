package chess

sealed trait Color {

  final def -(role: Role) = Piece(this, role)

  def unary_! : Color

  final def pawn = this - Pawn

  final val white = this == White
  final val black = this == Black
}

case object White extends Color {
  def unary_! = Black
}
case object Black extends Color {
  def unary_! = White
}



object Color {


  case class Map[A](white: A, black: A) {
    def apply(color: Color) = if (color.white) white else black

    def map[B](f: A => B): Map[B] = copy(white = f(white), black = f(black))
  }

  object Map {
    def apply[A](f: Color => A): Map[A] = Map(white = f(White), black = f(Black))
  }

}


case class Piece(color: Color, role: Role)


sealed trait Mobility {
  val orig: Pos
  val dest: Pos
}


case class Sliding(
  orig: Pos,
  dest: Pos,
  blocks: List[Pos],
  captures: Option[Pos]) extends Mobility

case class PawnPush(
  orig: Pos,
  dest: Pos,
  blocks: List[Pos]) extends Mobility

case class PawnCapture(
  orig: Pos,
  dest: Pos,
  captures: Option[Pos]) extends Mobility

case class Board(
  pieces: PosMap[Piece]
) {


  def apply(at: Pos): Option[Piece] = pieces get at
  
  def drop(piece: Piece, at: Pos): Board =
    copy(pieces = pieces + (at -> piece))

  def take(at: Pos): Board =
    copy(pieces = pieces - at)


  def sliding(at: Pos, role: SlidingRole): List[Sliding] =
    role.rays get at map (_.map { 
    { ray =>
      val orig = ray.orig
      val dest = ray.dest
      val blocks = ray.between.filter(this.apply(_) isDefined)
      val captures = this.apply(ray.dest) map (_ => ray.dest)
      Sliding(orig, dest, blocks, captures)
    }
    }) getOrElse Nil


  def pawnPush(at: Pos, color: Color): List[PawnPush] =
    Pawn.pushRays(color) get at map (_.map {
    { ray =>
      val orig = ray.orig
      val dest = ray.dest
      val blocks = ray.between.filter(this.apply(_) isDefined)
      PawnPush(orig, dest, blocks) 
    }
    }) getOrElse Nil


}


object Board {

  def symmetricRank(rank: IndexedSeq[Role]): Map[Pos, Piece] =
    (for (y <- Seq(A, B, G, H); x <- Upos.all) yield {
      Pos(x, y) -> (y match {
        case A => White - rank(x.index)
        case B => White.pawn
        case G => Black.pawn
        case H => Black - rank(x.index)
      })
    }) to(Map)

  val backRank = Vector(Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook)

  val pieces = symmetricRank(backRank)

  def init: Board = Board(pieces)

}
