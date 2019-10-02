// Referentially Transparent
val x = "Hello World"

// They are the same
val r1 = x.reverse
val r2 = x.reverse

// Replace X with the expression and evaluate the same
val r3 = "Hello World".reverse
val r4 = "Hello World".reverse

// Not referentially transparent
val t = new java.lang.StringBuilder("Hello")

// Same input different evaluation
val r5 = t.append(", world").toString
val r6 = t.append(", world").toString
