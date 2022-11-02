#lang racket
( require racket/trace )

( define ( ranks rank )
   ( list
     ( list rank 'C )
     ( list rank 'D )
     ( list rank 'H )
     ( list rank 'S )
     )
   )
( define ( deck )
   ( append
     ( ranks 2 )
     ( ranks 3 )
     ( ranks 4 )
     ( ranks 5 )
     ( ranks 6 )
     ( ranks 7 )
     ( ranks 8 )
     ( ranks 9 )
     ( ranks 'X )
     ( ranks 'J )
     ( ranks 'Q )
     ( ranks 'K )
     ( ranks 'A )
     )
   )
( define ( pick-a-card )
   ( define cards ( deck ) )
   ( list-ref cards ( random ( length cards ) ) )
   )
( define ( show card )
   ( display ( rank card ) )
   ( display ( suit card ) )
   )
( define ( rank card )
   ( car card )
   )
( define ( suit card )
   ( cadr card )
   )
( define ( red? card )
   ( or
     ( equal? ( suit card ) 'D )
     ( equal? ( suit card ) 'H )
     )
   )
( define ( black? card )
   ( not ( red? card ) )
   )
( define ( aces? card1 card2 )
   ( and
     ( equal? ( rank card1 ) 'A )
     ( equal? ( rank card2 ) 'A )
     )
   )

( define ( pick-two-cards )
   ( define cards ( list ( pick-a-card ) ( pick-a-card ) ) )
   ( cond
      ( ( equal? ( car cards ) ( cadr cards ) )
        ( pick-two-cards )
        )
      ( else cards )
      )
   )

( define ( higher-rank c1 c2 )
   ( define rank-order '( 2 3 4 5 6 7 8 9 X J Q K A ) )
   ( define c1-rank ( car c1 ) )
   ( define c2-rank ( car c2 ) )
   ( define rank-compare ( - ( index-of rank-order c1-rank) ( index-of rank-order c2-rank ) ) )
   ( cond
      ( ( > rank-compare 0 ) c1-rank )
      ( ( <= rank-compare 0 ) c2-rank )
      )
   )

; ( trace higher-rank )

( define ( classify-two-cards-ur cards )
   ( display cards )
   ( display ": " )

   ( define c1 ( car cards ) )
   ( define c2 ( cadr cards ) )

   ( define high-rank ( higher-rank c1 c2 ) )
   ( display high-rank )
   ( define flush? ( eq? ( cadr c1 ) ( cadr c2 ) ) )
   ( define pair? ( eq? ( car c1 ) ( car c2 ) ) )

   ( define rank-order '( 2 3 4 5 6 7 8 9 X J Q K A ) )
   ( define straight? ( or
                        ( = ( + ( index-of rank-order ( car c1 ) ) 1 ) ( index-of rank-order ( car c2 ) ) )
                        ( = ( - ( index-of rank-order ( car c1 ) ) 1 ) ( index-of rank-order ( car c2 ) ) )
                        )
      )

   ( cond
      ( pair? ( display " pair" ) )
      ( else ( display " high" ) )
      )

   ( cond ( straight? ( display " straight" ) ) )
   ( cond ( flush? ( display " flush" ) ) )
   )

( define ( classify-two-cards cards )
   ( display cards )
   ( display ": " )

   ( define rank-order '( 2 3 4 5 6 7 8 9 X J Q K A ) )
   ( define rank-name-parallel '( two three four five six seven eight nine ten jack queen king ace ) )

   ( define c1 ( car cards ) )
   ( define c2 ( cadr cards ) )

   ( define high-rank ( higher-rank c1 c2 ) )
   ( define high-rank-name ( list-ref rank-name-parallel ( index-of rank-order high-rank ) ) )
   ( display high-rank-name )
   
   ( define flush? ( eq? ( cadr c1 ) ( cadr c2 ) ) )
   ( define pair? ( eq? ( car c1 ) ( car c2 ) ) )

   ( define straight? ( or
                        ( = ( + ( index-of rank-order ( car c1 ) ) 1 ) ( index-of rank-order ( car c2 ) ) )
                        ( = ( - ( index-of rank-order ( car c1 ) ) 1 ) ( index-of rank-order ( car c2 ) ) )
                        )
      )
   

   ( cond
      ( pair? ( display " pair" ) )
      ( else ( display " high" ) )
      )

   ( cond ( straight? ( display " straight" ) ) )
   ( cond ( flush? ( display " flush" ) ) )
   )