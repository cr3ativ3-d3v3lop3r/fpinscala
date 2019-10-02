import scala.annotation.tailrec

// Exercise 2.2: Implement a polymorphic function to check whether
// an `Array[A]` is sorted
def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
  @tailrec
  def go(as: List[A]): Boolean = as match {
    case a if a.isEmpty => false // Empty list
    case _ :: Nil => true // 1 element list
    case head :: tail if ordered(head, tail.head) => go(tail) // Greater than 1 list
    case _ => false
  }

  go(as.toList)
}

val byLowestToHighest = (x: Int, y: Int) => x < y

isSorted(Array(1, 2, 20, 10), byLowestToHighest)

val byFirstLetter = (str1: String, str2: String) => str1.head.toLower < str2.head.toLower

isSorted(Array("Bobby", "Callum", "Zeus"), byFirstLetter)