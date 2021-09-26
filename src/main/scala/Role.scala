package chess

sealed trait Role:

  val forsyth: Char
  lazy val pgn: Char = forsyth.toUpper

end Role

sealed trait RayRole extends Role:

  val dirs: List[Dir]
  val long: Boolean

  def projections: List[Int] = if long then
    List(1, 2, 3, 4, 5, 6, 7)
  else
    List(1)

  lazy val rays: Map[Pos, List[Ray]] = 
    Pos.all.map { orig =>
      orig -> (for {
        dir <- dirs
        proj <- projections
      } yield Ray(orig, dir, proj))
    }.toMap


end RayRole



case object Role:


  val all: List[Role] = List(King, Queen, Rook, Bishop, Knight, Pawn)

  val allByPgn: Map[Char, Role] = all.map{ p => (p.pgn -> p) }.toMap


  val rayRoles: List[RayRole] = List(King, Queen, Rook, Bishop, Knight)

end Role


case object King extends RayRole:
  val forsyth = 'k'
 
  val dirs = Queen.dirs
  val long = false
   
end King

case object Queen extends RayRole:
  val forsyth = 'q'

  val dirs = Rook.dirs ++ Bishop.dirs
  val long = true
end Queen

case object Rook extends RayRole:
  val forsyth = 'r'
  
  val dirs: List[Dir] = List(
    (_.mid, _.succ),
    (_.mid, _.pred),
    (_.succ, _.mid),
    (_.pred, _.mid))

  val long = true

end Rook

case object Bishop extends RayRole:
  val forsyth = 'b'
  
  val dirs: List[Dir] = List(
    (_.succ, _.succ),
    (_.succ, _.pred),
    (_.pred, _.succ),
    (_.pred, _.pred))

  val long = true

end Bishop

case object Knight extends RayRole:
  val forsyth = 'n'

  val dirs: List[Dir] = List(
    (_.succ2, _.succ),
    (_.succ2, _.pred),
    (_.pred2, _.succ),
    (_.pred2, _.pred),
    (_.succ, _.succ2),
    (_.succ, _.pred2),
    (_.pred, _.succ2),
    (_.pred, _.pred2))

  val long = false

end Knight

case object Pawn extends Role:

  val forsyth = 'p'

end Pawn

