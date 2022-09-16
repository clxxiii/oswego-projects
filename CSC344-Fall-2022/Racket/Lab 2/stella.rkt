#lang racket
( require 2htdp/image )
( define ( stella size count color1 color2 )
   ( define step ( / size count))
   ( draw-stella size step 1 count color1 color2 empty-image )
   )

( define ( draw-stella size step start count color1 color2 img )
   ( define diameter ( * step start ))
   ( cond
      ( ( = start count )
        img
       )
      (
       ( > count start )
       ( if
         ( even? start )
                (
        draw-stella size step ( + start 1) count color1 color2 ( overlay img ( circle diameter 'solid color1 ) )
        )
                       (
        draw-stella size step ( + start 1) count color1 color2 ( overlay img ( circle diameter 'solid color2 ) )
        )
         )
       )
      )
   )