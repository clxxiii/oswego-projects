tgl n = foldl (+) 0 [1..n]

triangleSequence n = map tgl [1..n]

vowelCount s = length ( filter ( \x -> x `elem` ['a','e','i','o','u'] ) s )

lcsim mFunc fFunc list = map mFunc ( filter fFunc list ) 
