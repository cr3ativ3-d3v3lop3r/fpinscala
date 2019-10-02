case class CreditCard()

case class Coffee(price: Double)

case class Charge(creditCard: CreditCard, amount: Double) {

  def combine(other: Charge): Charge =
    if (creditCard == other.creditCard)
      Charge(creditCard, amount + other.amount)
    else
      throw new Exception("Can't combine charges to different cards")
}

class Cafe {
  def buyCoffee(creditCard: CreditCard): (Coffee, Charge) = {
    val cup = Coffee(1.23)

    (cup, Charge(creditCard, cup.price))
  }

  def buyCoffees(creditCard: CreditCard, n: Int): (List[Coffee], Charge) = {
    val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(creditCard))
    val (coffees, charges) = purchases.unzip

    (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
  }

  def coalesce(charges: List[Charge]): List[Charge] =
    charges.groupBy(_.creditCard).values.map(_.reduce(_ combine _)).toList
}

val creditCard = CreditCard()

new Cafe().buyCoffees(creditCard, 2)

new Cafe().coalesce(List(
  Charge(creditCard, 1.23),
  Charge(creditCard, 2.80)))