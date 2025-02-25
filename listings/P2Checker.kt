object NoAlignInsideLoop : FirFunctionCallChecker(MppCheckerKind.Common) {
	// inside a `for' or `while' construct
	private fun CheckerContext.isInsideALoopWithoutAlignedOn(): Boolean =
		wrappingElementsUntil { it is FirWhileLoop }
			?.discardIfFunctionDeclaration()
			?.discardIfOutsideAggregateEntryPoint()
			?.none(isFunctionCallWithName(AggregateFunctionNames.ALIGNED_ON_FUNCTION_NAME)) ?: false

	// inside a function like `forEach' or `map' of Kotlin Collections
	private fun CheckerContext.isInsideIteratedFunctionWithoutAlignedOn(): Boolean =
		wrappingElementsUntil { it is FirFunctionCall && it.functionName() in collectionMembers }
			?.discardIfFunctionDeclaration()
			?.discardIfOutsideAggregateEntryPoint()
			?.none(isFunctionCallWithName(AggregateFunctionNames.ALIGNED_ON_FUNCTION_NAME)) ?: false

	// the `check' method verifies that the function is aggregate and uses these two methods...
}