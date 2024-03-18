------------------------------------------------------------------------
------------------------------------------------------------------------
------------------------------------------------------------------------
-- File: ditdah.hs
-- Data: Late November, 2021
-- Line: Simulation of a Morse code encoder.

------------------------------------------------------------------------
------------------------------------------------------------------------
-- Code to build the dictionary of Morse code symbols for the lower
-- case letters.

------------------------------------------------------------------------
-- Names for the representation of "dot" (spoken dit) and "dash"
-- (spoken dah)

dit = "-"
dah = "---"

------------------------------------------------------------------------
-- A special version of append. It will add one space between two
-- strings in a binary string append function.

(+++) x y = x ++ " " ++ y

------------------------------------------------------------------------
-- Entries for a dictionary of Morse code symbols corresponding to the
-- lower case letters.

a = ('a',dit+++dah)
b = ('b',dah+++dit+++dit+++dit)
c = ('c',dah+++dit+++dah+++dit)
d = ('d',dah+++dit+++dit)
e = ('e',dit)
f = ('f',dit+++dit+++dah+++dit)
g = ('g',dah+++dah+++dit)
h = ('h',dit+++dit+++dit+++dit)
i = ('i',dit+++dit)
j = ('j',dit+++dah+++dah+++dah)
k = ('k',dah+++dit+++dah)
l = ('l',dit+++dah+++dit+++dit)
m = ('m',dah+++dah)
n = ('n',dah+++dit)
o = ('o',dah+++dah+++dah)
p = ('p',dit+++dah+++dah+++dit)
q = ('q',dah+++dah+++dit+++dah)
r = ('r',dit+++dah+++dit)
s = ('s',dit+++dit+++dit)
t = ('t',dah)
u = ('u',dit+++dit+++dah)
v = ('v',dit+++dit+++dit+++dah)
w = ('w',dit+++dah+++dah)
x = ('x',dah+++dit+++dit+++dah)
y = ('y',dah+++dit+++dah+++dah)
z = ('z',dah+++dah+++dit+++dit)

------------------------------------------------------------------------
-- The dictionary for Morse code symbols corresponding to lower case
-- letters.

symbols = [a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z]

------------------------------------------------------------------------
------------------------------------------------------------------------
-- Mindful of the fact that the symbols variable is an association
-- list, assoc performs association list lookup, and is used in a
-- function to find the Morse code string corresponding to a particular
-- lower case letter.

assoc key alist = head [ (k,v) | (k,v) <- alist, k == key ]

find letter = snd $ assoc letter symbols

------------------------------------------------------------------------
------------------------------------------------------------------------
-- Helping functions for the Morse code encoders for a letter, a word,
-- and a message.

addletter x y = x ++ "   " ++ y

addword x y = x ++ "       " ++ y

droplast3 w = reverse ( drop 3 ( reverse w ) )

droplast7 w = reverse ( drop 7 ( reverse w ) )

------------------------------------------------------------------------
------------------------------------------------------------------------
-- Morse code encoders for a letter, a word, and a message.

encodeletter x = find x -- snd ( assoc x symbols )

encodeword w = droplast3 almost
   where almost = foldr addletter "" ( map encodeletter w )

encodemessage m = droplast7 almost
   where almost = foldr addword "" ( map encodeword ( words m ) )


