package chess

import cats.data.Validated


case class Move(
  piece: Piece,
  mobility: Mobility,
  situationBefore: Situation,
  after: Board) {

    def situationAfter = Situation(finalizeAfter, !piece.color)


    def finalizeAfter: Board = {
      val board = after
      board
    }
  }



case class Situation(board: Board, color: Color) {

  def move(from: Pos, to: Pos): Validated[String, Move] = {

   ??? 

  }
  
}

case class Game(
  situation: Situation
) {


  def apply(orig: Pos, dest: Pos): Validated[String, (Game, Move)] =
    situation.move(orig, dest) map { move =>
      apply(move) -> move
    }


  def apply(move: Move): Game = {
    val newSituation = move.situationAfter
    copy(situation = newSituation)
  }
}

object Game {


  def apply(board: Board): Game = apply(board, White)

  def apply(board: Board, color: Color): Game = new Game(Situation(board, color))

}
