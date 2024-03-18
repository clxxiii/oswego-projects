list2set [] = []
list2set (el:rest) = if el `elem` rest then list2set rest else el:list2set rest

count x lx =  sum [ if x == s then 1 else 0 | s <- lx ]
freqTable str = [(x, count x str) | x <- list2set str]
