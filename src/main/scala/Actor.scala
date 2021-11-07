package chess

// pawn push
// pawn push 2
// pawn capture
// pawn push promote
// pawn capture promote
// pawn en passant
// king castle
// king move
// king capture
// vision move
// vision capture


case class PawnPush(before: Board,
 piece: Piece,
 pos: Pos):

end PawnPush

case class VisionMove(before: Board,
  pos: Pos,
  dest: Pos):
 


end VisionMove
