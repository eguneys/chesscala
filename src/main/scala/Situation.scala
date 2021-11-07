package chess

case class Situation(board: Board, color: Color): 


  lazy val moves: List[Move] = ??? 

  def apply(move: Move): Situation = ???

end Situation

case object Situation:

  def apply(fen: String): Option[Situation] = Forsyth.<>(fen)  

end Situation
