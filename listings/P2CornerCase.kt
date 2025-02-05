// Nested function
for(/* loop condition */) {
	fun Aggregate<Int>.nested() {
		neighboring({ 2 * 2 })
	}
}

// AlignedOn outside the loop
alignedOn(/* ... */) {
	for(/* loop condition */) {
		neighboring({ 2 * 2 })
	}
}

// Loop outside the `aggregate` block
for (/* loop condition */) {
	aggregate {
		neighboring({ 2 * 2 })
	}
}