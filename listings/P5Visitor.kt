class FunctionCallWithAggregateParVisitor(private val context: CheckerContext) : FirVisitorVoid() {
	// ...

	private fun isInAlignedOnOrNestedFun(): Boolean = insideAlignedOn || insideNestedFun

	override fun visitFunctionCall(functionCall: FirFunctionCall) {
		if (functionCall.isAggregate(context.session)) {
			// Case 1
			if (functionCall.fqName() == ALIGNED_ON_FUNCTION_FQ_NAME) {
				insideAlignedOn = true
				functionCall.acceptChildren(this)
				insideAlignedOn = false
			} else if (!isInsideAlignedOnOrNestedFun()) {
				// Pattern detected
				found = true
			}
		} else if (functionCall.hasAggregateArgument() && !isInAlignedOnOrNestedFun()) {
			// Case 2
			val visitor = FunctionCallWithAggregateParVisitor(context) // recursion
			found = visitor.visitSuspiciousFunctionCallDeclaration(functionCall)
		}
	}

	// Function declaration
	override fun visitSimpleFunction(simpleFunction: FirSimpleFunction) {
		functionCounter++
		simpleFunction.body?.accept(this)
		functionCounter--
	}
}