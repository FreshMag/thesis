// Normal version
exchange(initial = 1) { field ->
	field.map { it + 1 }
}

// Version with yielding context
exchanging(initial = 1) { field ->
	val fieldResult = field.map { it + 1 }
	fieldResult.yielding { fieldResult.map { "return $it" } }
}