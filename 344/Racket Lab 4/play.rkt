#lang racket

( require 2htdp/image )

( define ( a-list l1 l2 )
   ( cond
      ( ( not ( = ( length l1 ) ( length l2) ) ) '() )
      ( ( = ( length l1) 0 ) '() )
      ( else
        ( cons
          ( cons ( car l1 ) ( car l2 ) )
          ( a-list ( cdr l1 ) (cdr l2 ) )
          )
        )
      )
   )

( define ( assoc el li )
   ( cond
      ( ( = ( length li ) 0 ) '() )
      ( ( equal? el ( car ( car li ) ) ) ( car li ) )
      ( else
        ( assoc el ( cdr li ) )
        )
      )
   )

( define pitch-classes '( c d e f g a b ) )
( define color-names '( blue green brown purple red yellow orange ) )

( define ( box color )
   ( overlay
     ( square 30 "solid" color )
     ( square 35 "solid" "black" )
     )
   )
( define boxes
   ( list
     ( box "blue" )
     ( box "green" )
     ( box "brown" )
     ( box "purple" )
     ( box "red" )
     ( box "gold" )
     ( box "orange" )
     )
   )
( define pc-a-list ( a-list pitch-classes color-names ) )
( define cb-a-list ( a-list color-names boxes ) )
( define ( pc->color pc )
   ( cdr ( assoc pc pc-a-list ) )
   )
( define ( color->box color )
   ( cdr ( assoc color cb-a-list ) )
   )

( define ( play  l )
   ( cond
      ( ( = ( length l ) 0 ) empty-image )
      ( else 
              ( beside ( color->box ( pc->color ( car l ) ) ) ( play ( cdr l ) ) )
               )
      )
   )