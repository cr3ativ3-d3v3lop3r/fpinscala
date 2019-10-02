import scala.annotation.tailrec

// Exercise 2.1 - Write a function to compute the nth fibonacci number
def fib(n: Int): BigInt = {
  @tailrec
  def go(i: Int, leftAcc: BigInt, rightAcc: BigInt): BigInt = n match {
    case 0 | 1 => n
    case iterator if iterator < 0 => 0
    case iterator if iterator == i => rightAcc
    case _ => go(i + 1, rightAcc, leftAcc + rightAcc)
  }

  go(1, 0, 1)
}

fib(10)