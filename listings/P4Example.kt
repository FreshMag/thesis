// example with anonymous function that takes parameter (pattern detected)
evolve(initial) { it ->
    // but `it` is not used inside the body
		10
}

// example with anonymous function that takes parameter (pattern not detected)
evolve(initial) { it ->
		// `it` is used inside the body
		it + 10
}

// example of a parameter-less anonymous function (pattern detected)
neighboring {
	val example = 0
}

// example of a parameter-less anonymous function (pattern not detected)
val example = 10
neighboring { example }