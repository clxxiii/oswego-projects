
reverseWords :: String -> String
reverseWords str =  unwords (reverse (words str))

averageWordLength str =  word_sum / list_length
    where word_sum = fromIntegral(sum ( map length (words str)))
          list_length = fromIntegral(length ( words str ))
