// One of the simplest examples of Pattern 5: this should raise a warning
fun delegate(aggregate: Aggregate<Int>) {
	aggregate.evolving(0) { ... } // Pattern detected
}

fun Aggregate<Int>.entry() {
	listOf(1, 2, 3).forEach { delegate(this) }
}

// One, more complex example of Pattern 5: this should NOT raise any warning
fun delegate(aggregate: Aggregate<Int>) {
	fun delegate2() {
		aggregate.alignedOn(0) {
			aggregate.evolving(0) { ... } // Pattern NOT detected
		}
	}
	delegate2()
}

fun Aggregate<Int>.entry() {
	listOf(1, 2, 3).forEach { delegate(this) }
}