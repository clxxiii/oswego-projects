list2set [] = []
list2set (el:rest) = if el `elem` rest then list2set rest else el:list2set rest

isPalindrome [] = True
isPalindrome [_] = True
isPalindrome list = head list == last list && isPalindrome innerList
    where innerList = drop 1 endRemovedList
          endRemovedList = reverse ( drop 1 ( reverse list ) )

collatz n = collatzSeq [n]

collatzSeq (1:hist) = reverse (1:hist)
collatzSeq history =
    if even num then collatzSeq (collatzEven:history) else collatzSeq (collatzOdd:history)
      where num = head history
            collatzEven = div (head history) 2 
            collatzOdd = 3 * head history + 1

