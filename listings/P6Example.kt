// Evolve + Neighboring substitutable by Share
evolve(initial) { value ->
	val newValue = value + 1 
	val field = neighboring(newValue) // use of neighboring
	field.max(0) // field reduction with max
}

// Share
share(initial) { value ->
  val newValue = value.map { it + 1 }
	newValue.max(0) // field reduction with max
}

// Evolve + Neighboring NOT substitutable by Share
evolve(initial) { value ->
	val newValue = value + 1 
	val field = neighboring(other) // separated use of neighboring 
	newValue // new value of the evolution
}