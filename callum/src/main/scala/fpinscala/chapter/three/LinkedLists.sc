// Pattern Matching

def sum(ints: List[Int]): Int = ints match {
  case Nil => 0
  case x :: xs => x + sum(xs)
}

def product(ds: List[Double]): Double = ds match {
  case Nil => 1.0
  case 0.0 :: _ => 0.0
  case x :: xs => x * product(xs)
}

// Exercise 3.1

// What will be the result of the following match expression?

val x = List(1, 2, 3, 4, 5) match {
  case x :: 2 :: 4 :: _ => x
  case Nil => 42
  case x :: y :: 3 :: 4 :: _ => x + y
  case h :: t => h + sum(t)
  case _ => 101
}

// The answer is 3

// Exercise 3.2

// Implement the function tail for removing the first element of a List.
// Note that the function takes constant time. What are different choices
// you could make in your implementation if the List is Nil? We’ll return
// to this question in the next chapter.

def tail[A]: List[A] => List[A] = {
  case Nil => Nil
  case _ :: xs => xs
}

tail(Nil)
tail(List(1))
tail(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

// Exercise 3.3

// Using the same idea, implement the function setHead for replacing the
// first element of a List with a different value.

def setHead[A]: (List[A], A) => List[A] = {
  case (Nil, _) => Nil
  case (_ :: Nil, value) => List(value)
  case (_ :: xs, value) => value :: xs
}

setHead(Nil, 10)
setHead(List(1), 10)
setHead(List(1, 2), 10)

// Exercise 3.4

// Generalize tail to the function drop, which removes the first n elements
// from a list. Note that this function takes time proportional only to the
// number of elements being dropped—we don’t need to make a copy of the entire List.

def drop[A]: (List[A], Int) => List[A] = {
  case (Nil, _) => Nil
  case (l, 0) => l
  case (_ :: xs, n) => drop(xs, n - 1)
}

drop(Nil, 1)
drop(List(1), 0)
drop(List(1), 1)
drop(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 5)

// Exercise 3.5

// Implement dropWhile, which removes elements from the List prefix as long as they
// match a predicate.

def dropWhile[A]: (List[A], A => Boolean) => List[A] = {
  case (x :: xs, f) if f(x) => dropWhile(xs, f)
  case (x :: xs, f) if !f(x) => x :: dropWhile(xs, f)
  case (Nil, _) => Nil
  case (l @ _ :: Nil, _) => l
}

dropWhile[Int](Nil, _ == 0)
dropWhile[Int](List(0, 1, 2), _ == 1)
dropWhile[Int](List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), _ < 5)
dropWhile[Int](List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), _ > 5)

// Exercise 3.5.1

// Improve drop while with currying, so that data types can be inferred by the
// Scala compiler.

def dropWhileCurried[A]: List[A] => (A => Boolean) => List[A] = {
  case x :: xs => f =>
    if (f(x))
      dropWhileCurried(xs)(f)
    else
      x :: dropWhileCurried(xs)(f)
  case Nil => _ => Nil
}

dropWhileCurried(Nil)(_ == 0)
dropWhileCurried(List(0, 1, 2))(_ == 1)
dropWhileCurried(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))(_ < 5)
dropWhileCurried(List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10))(_ > 5)

// Exercise 3.6

// Not everything works out so nicely. Implement a function, init, that returns a
// List consisting of all but the last element of a List. So, given List(1,2,3,4),
// init will return List(1,2,3). Why can’t this function be implemented in constant
// time like tail?

def init[A]: List[A] => List[A] = {
  case Nil => Nil
  case _ :: Nil => Nil
  case x :: xs => x :: init(xs)
}

init(Nil)
init(List(1))
init(List(1, 2))
init(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
