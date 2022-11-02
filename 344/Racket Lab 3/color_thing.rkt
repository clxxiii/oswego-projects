#lang racket
( require 2htdp/image )
( define ( color-thing )
   ( display "? " )
   ( define input ( read ) )
   
   ( define command ( car input ) )
   ( define list ( cadr input ) )
   ( cond
      (
       ( eq? command 'random )
       
       ( define the-element ( list-ref list ( random ( length list ) ) ) )
       ( display-color the-element ) ( display "\n" )
      )

      (
       ( eq? command 'all )

       ( all-colors list )
      )

      (
       else

       ( display-color ( list-ref list ( - command 1 ) ) ) ( display "\n" )
       )
   )
   ( color-thing )
   )

( define ( all-colors list )
   ( cond
      (
       ( = ( length list ) 0 )

       ( display "" )
       )

      (
       else

       ( display-color ( car list) ) ( display "\n" )
       ( all-colors ( cdr list ) )
       )
   )
   )

( define ( display-color c )
   ( display ( rectangle 500 25 'solid c ) )
   )

( color-thing )