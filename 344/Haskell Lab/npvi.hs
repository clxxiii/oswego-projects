-- Test data
a :: [Int]
a = [2,5,1,3]

b :: [Int]
b = [1,3,6,2,5]

c :: [Int]
c = [4,4,2,1,1,2,2,4,4,8]

u :: [Int]
u = [2,2,2,2,2,2,2,2,2,2]

x :: [Int]
x = [1,9,2,8,3,7,2,8,1,9]


pairwiseValues :: [Int] -> [(Int, Int)]
pairwiseValues ls = zip ls ( tail ls )

pairwiseDifferences :: [Int] -> [Int]
pairwiseDifferences ls = map (\ (x,y) -> x - y ) ( pairwiseValues ls )

pairwiseSums :: [Int] -> [Int]
pairwiseSums ls = map (\ (x,y) -> x + y ) ( pairwiseValues ls )

half :: Int -> Double
half number = fromIntegral number / 2

pairwiseHalves :: [Int] -> [Double]
pairwiseHalves = map half

pairwiseHalfSums :: [Int] -> [Double]
pairwiseHalfSums ls = map half ( pairwiseSums ls )

pairwiseTermPairs :: [Int] -> [(Int, Double)]
pairwiseTermPairs ls = zip ( pairwiseDifferences ls ) ( pairwiseHalfSums ls )

term :: (Int,Double) -> Double
term ndPair = abs ( fromIntegral ( fst ndPair ) / ( snd ndPair ) )

pairwiseTerms :: [Int] -> [Double]
pairwiseTerms ls = map term ( pairwiseTermPairs ls )

nPVI :: [Int] -> Double
nPVI xs = normalizer xs * sum ( pairwiseTerms xs )
          where normalizer xs = 100 / fromIntegral ( ( length xs ) - 1 )
