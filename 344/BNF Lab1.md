# 1

<statement> ::= ( <size-phrase> <color-phrase> <pattern-phrase> <shape-phrase> )

<size-phrase> ::= ( size <size-type> )
<color-phrase> ::= ( color <color-type> )
<pattern-phrase> ::= ( pattern <pattern-type> )
<shape-phrase> ::= ( shape <shape-type> )

<size-type> ::= big | medium | small
<color-type> ::= red | yellow | blue
<pattern-type> ::= striped | dotted | solid
<shape-type> ::= circle | square | triangle

# 2

<sqn> ::= 0 | <1-phr> | <2-phr> | <3-phr>

<0-phr> ::= 0<non-zero>
<1-phr> ::= 1<non-one>
<2-phr> ::= 2<non-two>
<3-phr> ::= 3<non-three>

<non-zero> ::= <1-phr> | <2-phr> | <3-phr> | <empty>
<non-one> ::= <0-phr> | <2-phr> | <3-phr> | <empty>
<non-two> ::= <1-phr> | <0-phr> | <3-phr> | <empty>
<non-three> ::= <1-phr> | <2-phr> | <0-phr> | <empty>

# 3

<fours> ::= <1-seq> <2-seq> <3-seq> <4-seq>

<1-seq> ::= <1-list> <1-seq> | <empty>
<2-seq> ::= <2-list> <2-seq> | <empty>
<3-seq> ::= <3-list> <3-seq> | <empty>
<4-seq> ::= <4-list> <4-seq> | <empty>

<1-list> ::= ( 1 1 1 1 ) | <empty>
<2-list> ::= ( 1 1 2 ) ( 1 2 1 ) ( 2 1 1 ) | <empty>
<3-list> ::= ( 3 1 ) ( 1 3 ) | <empty>
<4-list> ::= ( 4 ) | <empty>

# 4

<exp> ::= <stmt> | <and-phr> | <or-phr> | <not-phr>
<exp-list> ::= <exp> <exp-list> | <empty>

<and-phr> ::= ( and <exp-list> )
<or-phr> :: = ( or <exp-list> )
<not-phr> ::= ( not <exp> )
<stmt> ::= #t | #f

# 5

<cmd> ::= <add-cmd> | <show-cmd> | <desc-cmd> | colors | exit

<add-cmd> ::= add <color> <var-name>
<show-cmd> ::= show <var-name>
<desc-cmd> ::= describe <var-name>

<color> ::= color | ( <r> <g> <b> ) | ( <r> <g> <b> <a> )
