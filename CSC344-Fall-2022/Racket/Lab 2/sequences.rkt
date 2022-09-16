#lang racket

( define ( square n )
   (* n n)
   )
( define ( cube n )
   (* n n n)
   )

( define ( triangular n )
   ( cond
      ( (= n 1)
        1
        )
      ( else
        ( + n ( triangular ( - n 1 ) ) )
        )
      )
   )

( define ( sigma n )
   ( get-prime-sums n 0 1 )
   )

( define (get-prime-sums n sum i )
   ( cond
      (
       ( = n i )
       ( + sum n )
       )
      (
       ( = ( modulo n i) 0 )
       ( get-prime-sums n ( + sum i ) ( + i 1 ) )
       )
      ( else ( get-prime-sums n sum ( + i 1) ) )
      )
   )

( define ( sequence name n )
   ( cond
      ( (= n 1)
        ( display ( name 1 ) ) ( display " " )
        )
      ( else
        ( sequence name ( - n 1 ) )
        ( display ( name n ) ) ( display " " )
        )
      )
   )