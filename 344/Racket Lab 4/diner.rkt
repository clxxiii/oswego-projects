#lang racket

( define menu '( ( hotdog .  5.0 ) ( milkshake . 2.0 ) ( tea . 1.0 ) ( pancakes . 4.5 ) ( waffles . 3.5 ) ( frenchtoast . 6.5 ) ) )

( define sales 
'(tea
  milkshake
  hotdog
  tea
  tea
  pancakes
  tea
  pancakes
  hotdog
  waffles
  frenchtoast
  pancakes
  pancakes
  milkshake
  hotdog
  hotdog
  frenchtoast
  milkshake
  tea
  milkshake
  hotdog
  tea
  milkshake
  hotdog
  hotdog
  frenchtoast
  hotdog
  milkshake
  frenchtoast
  milkshake
  )
   )

( define ( price item menu )
   ( cond
      ( ( = ( length menu ) 0 ) '() )
      ( ( equal? item ( car ( car menu ) ) ) ( cdr ( car menu ) ) )
      ( else
        ( price item ( cdr menu ) )
        )
      )
   )

( define ( total sales item )
   ( define filtered-list ( filter ( lambda (el) (eq? el item) ) sales ) )
   ( define prices ( map ( lambda (el) ( price el menu ) ) filtered-list ) )
   ( foldr + 0 prices )
   )

