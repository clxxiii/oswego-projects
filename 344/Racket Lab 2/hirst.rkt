#lang racket
( require 2htdp/image )

( define ( random-color )
   ( color ( random 256 ) ( random 256 ) ( random 256 ) 255 )
   )

( define transparent ( color 0 0 0 0 ) )

( define ( draw-dot )
   ( circle 30 'solid ( random-color ) )
   )

( define ( row-of-dots n row )
   ( cond
      ( 
       ( = n 0 )
       row
       )
      (
       ( > n 0)
       ( define new-row ( beside row ( draw-dot ) (square 20 'solid transparent ) ) )
       ( row-of-dots (- n 1) new-row)
       )
      )
   )

( define (draw-row n)
   ( row-of-dots n empty-image )
   )

( define ( set-of-rows n r im )
   ( cond
      ( 
       ( = n 0 )
       im
       )
      (
       ( > n 0)
       ( define new-set ( above im ( draw-row r ) (square 20 'solid transparent ) ) )
       ( set-of-rows (- n 1) r new-set )
       )
      )
   )

( define ( hirst-dots n )
   ( set-of-rows n n empty-image )
   )