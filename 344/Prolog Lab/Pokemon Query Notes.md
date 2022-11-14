# Pokemon Queries

1. `cen(pikachu).`
2. `cen(raichu).`
3. `cen(N).`, then spam ;
4. `cen(N), write(N), nl, fail.`
5. `evolves(squirtle, wartortle).`
6. `evolves(wortortle, squirtle).`
7. `evolves(squirtle, blastoise).`
8. `evolves(CEN, EV1), evolves(EV1, EV2).`
9. `evolves(CEN, EV1), evolves(EV1, EV2), write(CEN), write(" --> "), write(EV2), nl, fail.`
10. `pokemon(name(N),_,_,_), write(N), nl, fail.`
11. `pokemon(name(N),fire,_,_), write(N), nl, fail.`
12. `pokemon(N,Type,_,_), write(nks(N,kind(Type))), nl, fail.`
13. `pokemon(name(N),_,_,attack(waterfall,_)).`
14. `pokemon(name(N),_,_,attack(poison-powder,_)).`
15. `pokemon(_,water,_,attack(A,_)), write(A), nl, fail.`
16. `pokemon(name(poliwhirl),_,hp(HP),,_).`
17. `pokemon(name(butterfree),_,hp(HP),,_).`
18. `pokemon(name(N),_,hp(HP),_), HP > 85, write(HP), nl, fail.`
19. `pokemon(N,_,_,attack(_,DMG)), DMG > 60, write(N), nl, fail.`
20. `pokemon(name(N),_,hp(HP),_), cen(N), write(N), write(": "), write(HP), nl, fail.`

