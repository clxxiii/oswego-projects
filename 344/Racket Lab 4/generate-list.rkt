#lang racket

( require 2htdp/image )

( define ( roll-die ) ( + ( random 6 ) 1 ) )
( define ( dot )
   ( circle ( + 10 ( random 41 ) ) "solid" ( random-color ) )
   )
( define ( diamond )
   ( rotate 45 ( square  ( random 201 ) 'solid ( random-color ) ))
   )

( define ( big-dot )
   ( circle ( + 10 ( random 201 ) ) "solid" ( random-color ) )
   )


( define ( random-color )
   ( color ( rgb-value ) ( rgb-value ) ( rgb-value ) )
   )
( define ( rgb-value )
   ( random 256 )
   )
( define ( sort-dots loc )
   ( sort loc #:key image-width < )
   )

( define ( generate-list n func )
   ( cond
      ( ( = n 0 ) '() )
      ( else
        ( cons ( func ) ( generate-list ( - n 1 ) func ) )
        )
      )
   )

( define ( diamond-design n )
   ( define diamond-list ( generate-list n diamond ) )
   ( foldr overlay empty-image ( sort-dots diamond-list ) )
   )
