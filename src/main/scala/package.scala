import cats.data.Validated

package object chess {


  type File = Upos
  type Rank = Upos
  type PosMap[A] = Map[Pos, A]

  type Projection = Int
  type Direction = Pos => Option[Pos]


  implicit final def toOrnicarValidated[E, A](a: Validated[E, A]) =
    new OrnicarValidated(a)


  final class OrnicarValidated[E, A](validated: Validated[E, A]) {
    def flatMap[EE >: E, B](f: A => Validated[EE, B]): Validated[EE, B] = validated.andThen(f)
  }
}
