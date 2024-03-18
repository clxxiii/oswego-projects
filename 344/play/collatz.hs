collatz n = collatzSeq [n]

collatzSeq (1:hist) = reverse (1:hist)
collatzSeq history =
    if even num then collatzSeq (collatzEven:history) else collatzSeq (collatzOdd:history)
      where num = head history
            collatzEven = div (head history) 2 
            collatzOdd = 3 * head history + 1

main = do
  print  ( sum ( map ( sum . collatz ) [1..1000000] ) )
