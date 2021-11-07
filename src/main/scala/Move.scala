package chess

case class Move(situationBefore: Situation,
  after: Board):

  def color = situationBefore.color

  def before: Board = situationBefore.board
  def situationAfter = Situation(after, !color)

end Move

