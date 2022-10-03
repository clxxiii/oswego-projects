#lang racket
( require lang/posn )
( require 2htdp/image )

( define color1 ( color 200 200 200 ) )
( define color2 ( color 100 100 100 ) )
( define color3 ( color 150 150 150 ) )

( define (my-creation)
   ( overlay/offset
     ( polygon ( list
                 ( make-posn -50 0)
                 ( make-posn 0 20)
                 ( make-posn 50 0)
                 ( make-posn 0 -20)
                 )
               "solid"
               color1
               )
     0
     40
     ( overlay/offset
       ( polygon ( list
                   ( make-posn -50 0)
                   ( make-posn -50 60)
                   ( make-posn 0 80)
                   ( make-posn 0 20)
                   )
                 "solid"
                 color2
                 )
       50
       0
       ( polygon ( list
                   ( make-posn 0 0)
                   ( make-posn 0 60)
                   ( make-posn -50 80)
                   ( make-posn -50 20)
                   )
                 "solid"
                 color3
                 )
       )
     )
   )