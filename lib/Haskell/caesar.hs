import Data.Char
import System.Environment

rot :: Int -> Char -> Char
rot n a
  | ord a >= 97 && ord a <= 122 =
      chr $ mod ((ord a) - 97 + n) 26 + 97
  | ord a >= 65 && ord a <= 90 =
      chr $ mod ((ord a) - 65 + n) 26 + 65
  | otherwise = a

cipher n = map (rot n)

main = do
  (arg:args) <- getArgs
  interact $ cipher (read arg :: Int)
