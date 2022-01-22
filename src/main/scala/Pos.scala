package chess


sealed trait Upos {
  def up: Option[Upos]
  def down: Option[Upos]


  def up2: Option[Upos] = up.flatMap(_.up)
  def down2: Option[Upos] = down.flatMap(_.down)

  def index: Int

}

case object A extends Upos {
  def up = Some(B)
  def down = None

  def index = 0
}

case object B extends Upos {
  def up = Some(C)
  def down = Some(A)
  def index = 1
}

case object C extends Upos {
  def up = Some(D)
  def down = Some(B)
  def index = 2
}

case object D extends Upos {
  def up = Some(E)
  def down = Some(C)
  def index = 3
}

case object E extends Upos {
  def up = Some(F)
  def down = Some(D)
  def index = 4
}
case object F extends Upos {
  def up = Some(G)
  def down = Some(E)
  def index = 5
}
case object G extends Upos {
  def up = Some(H)
  def down = Some(F)
  def index = 6
}

case object H extends Upos {
  def up = None
  def down = Some(G)
  def index = 7
}


object Upos {

  def all: List[Upos] = List(A, B, C, D, E, F, G, H)
}

case class Pos(file: File, rank: Rank) {

  def up = rank.up map (Pos(file, _))
  def down = rank.down map (Pos(file, _))
  def left = file.down map (Pos(_, rank))
  def right = file.up map (Pos(_, rank))

  def up2 = rank.up2 map (Pos(file, _))
  def down2 = rank.down2 map (Pos(file, _))
  def left2 = file.down2 map (Pos(_, rank))
  def right2 = file.up2 map (Pos(_, rank))

  def project(proj: Projection, dir: Direction): List[Pos] =
    dir(this) map { p =>
      p :: p.project(proj - 1, dir)
    } getOrElse Nil

}

case object Pos {

  val A1 = new Pos(A, A)
  val A2 = new Pos(A, B)
  val A3 = new Pos(A, C)
  val A4 = new Pos(A, D)
  val A5 = new Pos(A, E)
  val A6 = new Pos(A, F)
  val A7 = new Pos(A, G)
  val A8 = new Pos(A, H)
  val B1 = new Pos(B, A)
  val B2 = new Pos(B, B)
  val B3 = new Pos(B, C)
  val B4 = new Pos(B, D)
  val B5 = new Pos(B, E)
  val B6 = new Pos(B, F)
  val B7 = new Pos(B, G)
  val B8 = new Pos(B, H)
  val C1 = new Pos(C, A)
  val C2 = new Pos(C, B)
  val C3 = new Pos(C, C)
  val C4 = new Pos(C, D)
  val C5 = new Pos(C, E)
  val C6 = new Pos(C, F)
  val C7 = new Pos(C, G)
  val C8 = new Pos(C, H)
  val D1 = new Pos(D, A)
  val D2 = new Pos(D, B)
  val D3 = new Pos(D, C)
  val D4 = new Pos(D, D)
  val D5 = new Pos(D, E)
  val D6 = new Pos(D, F)
  val D7 = new Pos(D, G)
  val D8 = new Pos(D, H)
  val E1 = new Pos(E, A)
  val E2 = new Pos(E, B)
  val E3 = new Pos(E, C)
  val E4 = new Pos(E, D)
  val E5 = new Pos(E, E)
  val E6 = new Pos(E, F)
  val E7 = new Pos(E, G)
  val E8 = new Pos(E, H)
  val F1 = new Pos(F, A)
  val F2 = new Pos(F, B)
  val F3 = new Pos(F, C)
  val F4 = new Pos(F, D)
  val F5 = new Pos(F, E)
  val F6 = new Pos(F, F)
  val F7 = new Pos(F, G)
  val F8 = new Pos(F, H)
  val G1 = new Pos(G, A)
  val G2 = new Pos(G, B)
  val G3 = new Pos(G, C)
  val G4 = new Pos(G, D)
  val G5 = new Pos(G, E)
  val G6 = new Pos(G, F)
  val G7 = new Pos(G, G)
  val G8 = new Pos(G, H)
  val H1 = new Pos(H, A)
  val H2 = new Pos(H, B)
  val H3 = new Pos(H, C)
  val H4 = new Pos(H, D)
  val H5 = new Pos(H, E)
  val H6 = new Pos(H, F)
  val H7 = new Pos(H, G)
  val H8 = new Pos(H, H)

  val all = List(
    A1, A2, A3, A4, A5, A6, A7, A8,
    B1, B2, B3, B4, B5, B6, B7, B8,
    C1, C2, C3, C4, C5, C6, C7, C8,
    D1, D2, D3, D4, D5, D6, D7, D8,
    E1, E2, E3, E4, E5, E6, E7, E8,
    F1, F2, F3, F4, F5, F6, F7, F8,
    G1, G2, G3, G4, G5, G6, G7, G8,
    H1, H2, H3, H4, H5, H6, H7, H8)

}

case class Ray(orig: Pos, dest: Pos, between: List[Pos])

case object Ray {


  def apply(orig: Pos, dir: Direction, proj: Projection): Option[Ray] = {

    val between = orig.project(proj, dir)
    val dest = dir(between.lastOption.getOrElse(orig))

    dest map { Ray(orig, _, between) }
  }


}


sealed trait Role

sealed trait SlidingRole extends Role {
  val dirs: List[Direction]
  val projections: List[Projection]

  lazy val rays: PosMap[List[Ray]] = Pos.all map { p =>
    p -> (dirs flatMap { dir =>
      projections flatMap { projection =>
        Ray(p, dir, projection)
      }
    })
  } to(Map)

}

case object King extends SlidingRole {
  val dirs = Queen.dirs
  val projections = Knight.projections
}

case object Queen extends SlidingRole {
  val dirs = Rook.dirs ::: Bishop.dirs
  val projections = Rook.projections
}

case object Rook extends SlidingRole {
  val dirs = List(_.up, _.down, _.left, _.right) 
  val projections = Bishop.projections
}


case object Bishop extends SlidingRole {
  val dirs = List(
    _.up flatMap (_.left),
    _.up flatMap (_.right),
    _.down flatMap (_.left),
    _.down flatMap (_.right)) 

  val projections = List(1, 2, 3, 4, 5, 6, 7)
}

case object Knight extends SlidingRole {
  val dirs = List(
    _.up2 flatMap (_.left),
    _.up2 flatMap (_.right),
    _.down2 flatMap (_.left),
    _.down2 flatMap (_.right),
    _.up flatMap (_.left2),
    _.up flatMap (_.right2),
    _.down flatMap (_.left2),
    _.down flatMap (_.right2),
    )
  val projections = List(1)
}


case object Pawn extends Role {


  val dirs: Color.Map[(Direction, Rank)] = Color.Map(
    (_.up, B), (_.down, G))


  val pushRays: Color.Map[PosMap[List[Ray]]] = dirs map {
    dirs =>
      Pos.all map { p =>
        val projection = if (p.rank == dirs._2) List(1) else List(1, 2)
        p -> projection.flatMap { Ray(p, dirs._1, _) }
      } to(Map)
  }

}
