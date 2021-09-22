package chess

sealed trait Eos:
  val succ: Option[Eos]
  val pred: Option[Eos]
  lazy val succ2 = succ.flatMap(_.succ)
  lazy val pred2 = pred.flatMap(_.pred)
  val mid = Some(this)

  val value: Int

end Eos

case object A extends Eos:
  val value = 1
  val pred = None 
  val succ = Some(B)
end A

case object B extends Eos:
  val value = 2
  val pred = Some(A) 
  val succ = Some(C)
end B

case object C extends Eos:
  val value = 3
  val pred = Some(B) 
  val succ = Some(D)
end C

case object D extends Eos:
  val value = 4
  val pred = Some(C)
  val succ = Some(E)
end D

case object E extends Eos:
  val value = 5
  val pred = Some(D) 
  val succ = Some(F)
end E

case object F extends Eos:
  val value = 6
  val pred = Some(E) 
  val succ = Some(G)
end F

case object G extends Eos:
  val value = 7
  val pred = Some(F) 
  val succ = Some(H)
end G

case object H extends Eos:
  val value = 8
  val pred = Some(G) 
  val succ = None 
end H

type Eir = Eos => Option[Eos]
type Dir = (Eir, Eir)

case object Eos:

  val all: List[Eos] = List(A, B, C, D, E, F, G, H)

  val allByInt: Map[Int, Eos] = all.map(p => p.value -> p).toMap


  val rook: List[Dir] = List((_.succ, _.mid),
    (_.pred, _.mid),
    (_.mid, _.succ),
    (_.mid, _.pred))
 
  val bishop: List[Dir] = List((_.succ, _.succ),
    (_.succ, _.pred),
    (_.pred, _.succ),
    (_.pred, _.pred))

  val king = rook ++ bishop
  val queen = rook ++ bishop

  val knight: List[Dir] = List[Dir]((_.succ2, _.succ),
    (_.succ2, _.pred),
    (_.pred2, _.succ),
    (_.pred2, _.pred),
    (_.succ, _.succ2),
    (_.succ, _.pred2),
    (_.pred, _.succ2),
    (_.pred, _.pred2))

end Eos

type File = Eos
type Rank = Eos

case class Pos(file: File, rank: Rank):

  def dir(dir: Dir): Option[Pos] =
    for {
      f2 <- dir._1(file)
      r2 <- dir._2(rank)
    } yield Pos(f2, r2)

end Pos

case object Pos:

  def at(x: Int, y: Int): Option[Pos] =
    for {
      ex <- Eos.allByInt get x
      ey <- Eos.allByInt get y
    } yield Pos(ex, ey)

  val A1 = Pos(A, A)
  val A2 = Pos(A, B)
  val A3 = Pos(A, C)
  val A4 = Pos(A, D)
  val A5 = Pos(A, E)
  val A6 = Pos(A, F)
  val A7 = Pos(A, G)
  val A8 = Pos(A, H)

  val B1 = Pos(B, A)
  val B2 = Pos(B, B)
  val B3 = Pos(B, C)
  val B4 = Pos(B, D)
  val B5 = Pos(B, E)
  val B6 = Pos(B, F)
  val B7 = Pos(B, G)
  val B8 = Pos(B, H)

  val C1 = Pos(C, A)
  val C2 = Pos(C, B)
  val C3 = Pos(C, C)
  val C4 = Pos(C, D)
  val C5 = Pos(C, E)
  val C6 = Pos(C, F)
  val C7 = Pos(C, G)
  val C8 = Pos(C, H)

  val D1 = Pos(D, A)
  val D2 = Pos(D, B)
  val D3 = Pos(D, C)
  val D4 = Pos(D, D)
  val D5 = Pos(D, E)
  val D6 = Pos(D, F)
  val D7 = Pos(D, G)
  val D8 = Pos(D, H)

  val E1 = Pos(E, A)
  val E2 = Pos(E, B)
  val E3 = Pos(E, C)
  val E4 = Pos(E, D)
  val E5 = Pos(E, E)
  val E6 = Pos(E, F)
  val E7 = Pos(E, G)
  val E8 = Pos(E, H)

  val F1 = Pos(F, A)
  val F2 = Pos(F, B)
  val F3 = Pos(F, C)
  val F4 = Pos(F, D)
  val F5 = Pos(F, E)
  val F6 = Pos(F, F)
  val F7 = Pos(F, G)
  val F8 = Pos(F, H)
  
  val G1 = Pos(G, A)
  val G2 = Pos(G, B)
  val G3 = Pos(G, C)
  val G4 = Pos(G, D)
  val G5 = Pos(G, E)
  val G6 = Pos(G, F)
  val G7 = Pos(G, G)
  val G8 = Pos(G, H)


  val H1 = Pos(H, A)
  val H2 = Pos(H, B)
  val H3 = Pos(H, C)
  val H4 = Pos(H, D)
  val H5 = Pos(H, E)
  val H6 = Pos(H, F)
  val H7 = Pos(H, G)
  val H8 = Pos(H, H)

end Pos
