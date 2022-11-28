squareArea x = x * x

circleArea rad = radsq * pi
    where radsq = rad * rad

blueAreaOfCube side = 6 * ( sarea - carea )
    where sarea = squareArea side
          carea = circleArea (side / 4)

paintedCube1 n = if n > 2 then squareArea (n - 2) * 6 else 0

paintedCube2 n = if n > 2 then n * 12 else 0
