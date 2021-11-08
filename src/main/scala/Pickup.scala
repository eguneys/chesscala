package chess

case class Pickup(orig: Pos, piece: Piece):

end Pickup

case class Drop(orig: Pos, piece: Piece):
  lazy val pickup = Pickup(orig, piece)
end Drop


case class LongRange(drop: Drop, 
  target: (Pos, Option[Drop]), 
  between: Map[Pos, Option[Drop]]):

  def apply(drop2: Drop): LongRange =
    if drop2.orig == target._1 then
      copy(target = (target._1, Some(drop2)))
    else
      between.get(drop2.orig) match {
        case None => this
        case _ => copy(between = between + ((drop2.orig, Some(drop2))))
      }


  def apply(pickup2: Pickup): LongRange = 
    if pickup2.orig == target._1 then
      copy(target = (target._1, None))
    else
      between.get(pickup2.orig) match {
        case None => this
        case _ => copy(between = between - pickup2.orig)
      }
end LongRange

case class ShortRange(drop: Drop, target: (Pos, Option[Drop])):

end ShortRange


case class Drops(
  drops: Map[Pos, Drop],
  longs: Map[Pos, LongRange],
  shorts: Map[Pos, ShortRange]
):

  def apply(drop: Drop) = {
    
  }

end Drops
