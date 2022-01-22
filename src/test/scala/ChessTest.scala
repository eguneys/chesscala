package chess

import cats.data.Validated
import org.specs2.matcher.ValidatedMatchers
import org.specs2.mutable.Specification

abstract class ChessTest extends Specification with ValidatedMatchers {

  def makeBoard: Board = Board init

  def makeGame: Game = Game(makeBoard, White)


  case class RichGame(game: Game) {


    def playMoves(moves: (Pos, Pos)*): Validated[String, Game] = playMoveList(moves)

    def playMoveList(moves: Iterable[(Pos, Pos)]): Validated[String, Game] = {

      val vg = moves.foldLeft(Validated.valid(game): Validated[String, Game]) { (vg, move) =>
        
        val ng = vg flatMap { g => 
          g(move._1, move._2) map (_._1)
        }
        ng
      }
      vg
    }

  }

  implicit def richGame(game: Game): RichGame = RichGame(game)

}
