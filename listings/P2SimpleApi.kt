fun CheckerContext.wrappingElementsUntil(
		predicate: (FirElement) -> Boolean,
): List<FirElement>? {
	// ...
	return containingElements
			.takeIf { it.any(predicate) }
			// ...
			?.dropLast(1) // the element itself
			?.takeLastWhile { !predicate(it) }
}

fun List<FirElement>.discardIfFunctionDeclaration(): List<FirElement>? =
	takeIf { elements -> elements.none { it is FirSimpleFunction } }

fun List<FirElement>.discardIfOutsideAggregateEntryPoint(): List<FirElement>? =
	takeIf { it.none(isFunctionCallsWithName("aggregate")) }

fun isFunctionCallWithName(name: String): ((FirElement) -> Boolean) = {
	it is FirFunctionCall && it.functionName() == name
}



