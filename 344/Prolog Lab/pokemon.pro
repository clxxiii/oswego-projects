% -----------------------------------------------------------------------
% -----------------------------------------------------------------------
% --- File: pokemon.pro
% --- Line: Just a few facts about pokemon
% -----------------------------------------------------------------------

% -----------------------------------------------------------------------
% --- cen(P) :: Pokemon P was "creatio ex nihilo"

cen(pikachu).
cen(bulbasaur).
cen(caterpie).
cen(charmander).
cen(vulpix).
cen(poliwag).
cen(squirtle).
cen(staryu).

% -----------------------------------------------------------------------
% --- evolves(P,Q) :: Pokemon P directly evolves to pokemon Q

evolves(pikachu,raichu).
evolves(bulbasaur,ivysaur).
evolves(ivysaur,venusaur).
evolves(caterpie,metapod).
evolves(metapod,butterfree).
evolves(charmander,charmeleon).
evolves(charmeleon,charizard).
evolves(vulpix,ninetails).
evolves(poliwag,poliwhirl).
evolves(poliwhirl,poliwrath).
evolves(squirtle,wartortle).
evolves(wartortle,blastoise).
evolves(staryu,starmie).

% -----------------------------------------------------------------------
% --- pokemon(name(N),T,hp(H),attach(A,D)) :: There is a pokemon with
% --- name N, type T, hit point value H, and attach named A that does
% --- damage D.

pokemon(name(pikachu), electric, hp(60), attack(gnaw, 10)).
pokemon(name(raichu), electric, hp(90), attack(thunder-shock, 90)).

pokemon(name(bulbasaur), grass, hp(40), attack(leech-seed, 20)).
pokemon(name(ivysaur), grass, hp(60), attack(vine-whip, 30)).
pokemon(name(venusaur), grass, hp(140), attack(poison-powder, 70)).

pokemon(name(caterpie), grass, hp(50), attack(gnaw, 20)).
pokemon(name(metapod), grass, hp(70), attack(stun-spore, 20)).
pokemon(name(butterfree), grass, hp(130), attack(whirlwind, 80)).

pokemon(name(charmander), fire, hp(50), attack(scratch, 10)).
pokemon(name(charmeleon), fire, hp(80), attack(slash, 50)).
pokemon(name(charizard), fire, hp(170), attack(royal-blaze, 100)).

pokemon(name(vulpix), fire, hp(60), attack(confuse-ray, 20)).
pokemon(name(ninetails), fire, hp(100), attack(fire-blast, 120)).

pokemon(name(poliwag), water, hp(60), attack(water-gun, 30)).
pokemon(name(poliwhirl), water, hp(80), attack(amnesia, 30)).
pokemon(name(poliwrath), water, hp(140), attack(dashing-punch, 50)).

pokemon(name(squirtle), water, hp(40), attack(bubble, 10)).
pokemon(name(wartortle), water, hp(80), attack(waterfall, 60)).
pokemon(name(blastoise), water, hp(140), attack(hydro-pump, 60)).

pokemon(name(staryu), water, hp(40), attack(slap, 20)).
pokemon(name(starmie), water, hp(60), attack(star-freeze, 20)).

% -----------------------------------------------------------------------
% --- Part 2

% Display the names of every pokemon
display_names :- pokemon(name(N),_,_,_), write(N), nl, fail.
display_names :- true.

% Display the name of every attack
display_attacks :- pokemon(_,_,_,attack(ATK,_)), write(ATK), nl, fail.
display_attacks :- true.

% Returns true if attack power is larger than 55
powerful(N) :- pokemon(name(N),_,_,attack(_,PWR)), PWR > 55.

% Returns true if HP is larget than 100
tough(N) :- pokemon(name(N),_,hp(HP),_), HP > 100.

% Get the type of a pokemon by name
type(N, T) :- pokemon(name(N),T,_,_).

% Write all pokemon with given type
dump_kind(T) :- pokemon(N,T,HP,ATK), write(pokemon(N,T,HP,ATK)), nl, fail.
dump_kind(_) :- true.

% Write all cen pokemon
display_cen :- pokemon(name(N),_,_,_), cen(N), write(N), nl, fail.
display_cen :- true.

% Display family of pokemon from base pokemon.
family(CEN) :- pokemon(name(CEN),_,_,_), cen(CEN), write(CEN), write(" "), evolves(CEN, EV1), write(EV1), write(" "), evolves(EV1, EV2), write(EV2).
family(CEN) :- cen(CEN).

% Display all families
families :- cen(C), family(C), nl, fail.
families :- true.

% This doesn't work and I don't know why
lineage(N) :- pokemon(name(N),T,H,A), write(pokemon(name(N),T,H,A)), evolves(N,E1), pokemon(name(E1),E1T,E1H,E1A), nl, write(pokemon(name(E1),E1T,E1H,E1A)), evolves(E1, E2), pokemon(name(E2),E2T,E2H,E2A), nl, write(pokemon(name(E2),E2T,E2H,E2A)).
lineage(_) :- true.

