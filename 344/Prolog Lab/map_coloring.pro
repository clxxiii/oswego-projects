% File: map_coloring.pro
% Line Program to find a 4 color map for the given image.


% different(X,Y) :: X is not equal to Y

different(red,blue).
different(red,green).
different(red,orange).
different(green,blue).
different(green,orange).
different(green,red).
different(blue,green).
different(blue,orange).
different(blue,red).
different(orange,blue).
different(orange,green).
different(orange,red).

coloring(ORING,LT, LR, LL, LB, MT1, MT2, MR1, MR2, MB1, MB2, ML1, ML2, S1, S2, S3, S4) :- 
    different(ORING, LT),
    different(ORING, LR),
    different(ORING, LL),
    different(ORING, LB),
    different(LT, LR),
    different(LR, LB),
    different(LB, LL),
    different(LL, LT),
    different(MT1, MT2),
    different(MT2, MR1),
    different(MR1, MR2),
    different(MR2, MB1),
    different(MB1, MB2),
    different(MB2, ML1),
    different(ML1, ML2),
    different(ML2, MT1),
    different(LT, MT1),
    different(LT, MT2),
    different(LR, MR1),
    different(LR, MR2),
    different(LL, ML1),
    different(LL, ML2),
    different(LB, MB1),
    different(LB, MB2),
    different(S1, MT1),
    different(S1, ML2),
    different(S2, MT2),
    different(S2, MR1),
    different(S3, MB1),
    different(S3, MB1),
    different(S4, MB2),
    different(S4, ML1),
    different(S1, S2),
    different(S2, S3),
    different(S3, S4),
    different(S4, S1).