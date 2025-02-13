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

	private fun CheckerContext.isIteratedWithoutAlignedOn(): Boolean =
		isInsideALoopWithoutAlignedOn() || isInsideIteratedFunctionWithoutAlignedOn()

	override fun check(
		expression: FirFunctionCall,
		context: CheckerContext,
		reporter: DiagnosticReporter,
	) {
		val calleeName = expression.functionName()
    if (expression.fqName() in safeOperators) return // exclude `alignedOn' and others
		if (expression.isAggregate(context.session) && context.isIteratedWithoutAlignedOn()) {
			// report as shown in the checker for Pattern 1
		}
	}
}