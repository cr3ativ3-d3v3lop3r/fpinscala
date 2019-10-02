// Exercise 2.3: Implement `curry`.

// Note that `=>` associates to the right, so we could
// write the return type as `A => B => C`

// Let’s look at another example, currying,9 which converts a
// function f of two arguments into a function of one argument
// that partially applies f. Here again there’s only one
// implementation that compiles. Write this implementation.

// NB: There is a method on the `Function` object in the standard library,
// `Function.uncurried` that you can use for uncurrying.

// Note that we can go back and forth between the two forms. We can curry
// and uncurry and the two forms are in some sense "the same". In FP jargon,
// we say that they are _isomorphic_ ("iso" = same; "morphe" = shape, form),
// a term we inherit from category theory.

def curry[A, B, C](f: (A, B) => C): A => B => C =
  a => b => f(a, b)

curry { (i: Int, str: String) =>
  i == str.toInt
}(10)("10")

// Exercise 2.4: Implement `uncurry`
def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)

uncurry { i: Int => str: String =>
  i == str.toInt
}