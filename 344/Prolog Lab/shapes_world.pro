% File: shapes_world.pro
% Line: Representation of shapes on a canvas


% square(N, side(L), color(C)) :: N is the name of a square 
% with side L and color C

square(sera, side(7), color(purple)).
square(sara, side(5), color(blue)).
square(sarah, side(11), color(red)).

% circle(N, radius(R), color(C)) :: N is the name of a circle
% with radius R and color C

circle(carla,radius(4),color(green)).
circle(cora,radius(7),color(blue)).
circle(connie,radius(3),color(purple)).
circle(claire,radius(5),color(green)).

% circles :: list the names of all of the circles
circles :- circle(Name,_,_), write(Name),nl,fail.
circles.

% squares :: list the names of all of the squares
squares :- square(Name,_,_), write(Name),nl,fail.
squares.

% shapes :: list the names of all of the shapes
shapes :- circles,squares.

% blue(Name) :: Name is a blue shape
blue(Name) :- square(Name,_,color(blue)).
blue(Name) :- circle(Name,_,color(blue)).

% --- large(Name) :: Name is a large shape
large(Name) :- area(Name,A), A >= 100.

% --- small(Name) :: Name is a small shape
small(Name) :- area(Name,A), A < 100.

% --- area(Name,A) :: A is the area of the shape with name Name
area(Name,A) :- circle(Name,radius(R),_), A is 3.14 * R * R.
area(Name,A) :- square(Name,side(S),_), A is S * S.