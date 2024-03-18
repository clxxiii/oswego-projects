#lang racket
( define ( asc num )
   ( list num ( + num 1 ) ( + num 2 ) )
   )

( define ( mlr el1 el2 el3 )
   ( list el3 el2 el1 )
   )

( define ( rn low high )
   ( round ( + ( * ( random ) ( - high low ) ) low ) )
   )