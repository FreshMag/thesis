// example with anonymous function that takes parameter (pattern detected)
evolve(initial) { value ->
    // but `value` is not used inside the body
		10
}

// example with anonymous function that takes parameter (pattern not detected)
evolve(initial) { value ->
		// `value` is used inside the body
		value + 10
}

// example of a parameter-less anonymous function (pattern detected)
neighboring {
	val example = 0
}

// example of a parameter-less anonymous function (pattern not detected)
val example = 10
neighboring { example }