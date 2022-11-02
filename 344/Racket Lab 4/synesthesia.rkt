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

; https://docs.google.com/spreadsheets/d/1t6yZEi1T0rPkgFPpuhTKIbwDE9o4aDEb7KSxULVig0Q/edit#gid=0
( define AI (text "A" 36 "orange") )
( define BI (text "B" 36 "red") )
( define CI (text "C" 36 "blue") )
( define DI (text "D" 36 ( color 214 194 199 )) )
( define EI (text "E" 36 ( color 61 79 133 )) )
( define FI (text "F" 36 ( color 81 167 248 )) )
( define GI (text "G" 36 ( color 80 183 67 )) )
( define HI (text "H" 36 ( color 121 32 29 )) )
( define II (text "I" 36 ( color 107 130 2 )) )
( define JI (text "J" 36 ( color 248 84 171 )) )
( define KI (text "K" 36 ( color 16 42 44 )) )
( define LI (text "L" 36 ( color 60 132 202 )) )
( define MI (text "M" 36 ( color 228 163 230 )) )
( define NI (text "N" 36 ( color 122 176 66 )) )
( define OI (text "O" 36 ( color 182 122 92 )) )
( define PI (text "P" 36 ( color 70 150 245 )) )
( define QI (text "Q" 36 ( color 28 182 88 )) )
( define RI (text "R" 36 ( color 185 61 106 )) )
( define SI (text "S" 36 ( color 94 85 62 )) )
( define TI (text "T" 36 ( color 254 30 131 )) )
( define UI (text "U" 36 ( color 243 121 109 )) )
( define VI (text "V" 36 ( color 38 128 93 )) )
( define WI (text "W" 36 ( color 109 151 21 )) )
( define XI (text "X" 36 ( color 106 27 152 )) )
( define YI (text "Y" 36 ( color 99 58 12 )) )
( define ZI (text "Z" 36 ( color 207 78 77 )) )

( define alphabet '(A B C D E F G H I J K L M N O P Q R S T U V W X Y Z) )
( define alphapic ( list AI BI CI DI EI FI GI HI II JI KI LI MI NI OI PI QI RI SI TI UI VI WI XI YI ZI ) )

( define a->i ( a-list alphabet alphapic ) )

( define ( letter->image letter )
   ( cdr ( assoc letter a->i ) )
   )

( define ( gcs list )
   ( define i-list ( map letter->image list ) )
   ( foldr beside empty-image i-list )
   )