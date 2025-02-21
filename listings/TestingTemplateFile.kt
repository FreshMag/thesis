fun Aggregate<Int>.entry() {
	%s
	for (j in listOf(1, 2, 3)) {
			%s
			neighboring(0)
			%s
	}
	%s
}