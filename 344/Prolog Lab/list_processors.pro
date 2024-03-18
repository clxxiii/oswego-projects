% ---------------------
% File: List Processors
% ---------------------

first([H|_], H).

rest([_|T], T).

last([H|[]], H).
last([_|T], Result) :- last(T, Result).


nth(0, [H|_], H).
nth(N, [_|T], E) :- K is N - 1, nth(K,T,E).

writelist([]).
writelist([H|T]) :- write(H), nl, writelist(T).

sum([], 0).
sum([H|T], Sum) :-
	sum(T, SumOfTail),
	Sum is H + SumOfTail.

add_first(X,L,[X|L]).

add_last(X,[],[X]).
add_last(X,[H|T],[H|TX]) :- add_last(X,T,TX).

iota(0,[]).
iota(N,IotaN) :-
	K is N - 1,
	iota(K, IotaK),
	add_last(N, IotaK, IotaN).

pick(L, Item) :-
	length(L, Length),
	random(0, Length, RN),
	nth(RN, L, Item).

make_set([], []).
make_set([H|T],TS) :-
	member(H, T),
	make_set(T, TS).
make_set([H|T],[H|TS]) :-
	make_set(T, TS).

% --------------------------
% List Processing Excersices
% --------------------------

product([], 1).
product([H|T], Product) :- 
	product(T, TailProduct),
	Product is H * TailProduct.

make_list(0,_,[]).
make_list(N,Element,List) :-
	K is N - 1,
	make_list(K,Element,Tail),
	List = [Element|Tail].

but_first([], []).
but_first([_|T], T).

but_last(List, Result) :-
	reverse(List, ReverseList),
	but_first(ReverseList, ButFirstList),
	reverse(ButFirstList, Result).

is_palindrome([]).
is_palindrome([_|[]]).
is_palindrome(List) :-
	first(List,FirstEl),
	last(List,LastEl),
	FirstEl = LastEl,
	but_first(List,ButFirst),
	but_last(ButFirst,TruncList),
	is_palindrome(TruncList).


% -----------------
% Sentence Building
% -----------------

noun_phrase(NP) :-
	Nouns = [king, knight, scientist, wizard, frog, dog, snake, book, sheriff],
	Adjs = [sick, mad, happy, ugly, shy, aggresive],
	pick(Nouns, N),
	pick(Adjs, A),
	NP = [the, A, N].

sentence(S) :-
	Verbs = [shot, fed, scared, carried, swiped, read, loved],
	pick(Verbs, V),
	noun_phrase(NP1),
	noun_phrase(NP2),
	append(NP1, [V|NP2], S).
