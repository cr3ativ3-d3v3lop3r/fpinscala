// Exercise 2.5: Implement `compose`

def compose[A, B, C](f: B => C, g: A => B): A => C =
  a => f(g(a))

compose((str: String) => str.toInt, (d: Double) => d.toString)