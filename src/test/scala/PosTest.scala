package chess

import Pos._

class PosTest extends ChessTest:

  


  "Pos.at Pos from integer" >> {
     "A1, H8" >> {
       Pos.at(1, 1) must beSome(A1)
       Pos.at(8, 1) must beSome(H1)
       Pos.at(8, 8) must beSome(H8)
       Pos.at(5, 5) must beSome(E5)
     }
  }


  "Ray" >> {
    "Knight @ a1" >> {

      Knight.rays(A1).flatMap(_.dest) must contain(allOf(C2, B3))
    }
  }

end PosTest
