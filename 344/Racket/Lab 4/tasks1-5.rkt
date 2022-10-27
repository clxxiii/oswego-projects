#lang racket

( define ( generate-uniform-list l form )
   ( cond
      ( ( = l 0) '() )
      ( else
        ( cons form ( generate-uniform-list ( - l 1 ) form ) )
        )
      )
   )

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

( define ( rassoc el li )
   ( cond
      ( ( = ( length li ) 0 ) '() )
      ( ( equal? el ( cdr ( car li ) ) ) ( car li ) )
      ( else
        ( rassoc el ( cdr li ) )
        )
      )
   )

( define ( los->s li )
   ( cond
      ( ( = ( length li ) 0 ) "" )
      ( else
        ( cond
           ( ( = ( length li ) 1 )
             ( string-append ( car li ) "" ( los->s ( cdr li ) ) )
             )
           ( else
             ( string-append ( car li ) " " ( los->s ( cdr li ) ) )
           )
           )
        )
      )
   )