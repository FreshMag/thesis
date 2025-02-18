private class YieldingReceiverVisitor : FirVisitorVoid() {
	// ...
	override fun visitFunctionCall(functionCall: FirFunctionCall) {
		if (functionCall.fqName() == YIELDING_FUNCTION_FQ_NAME) {
			returnExpression = functionCall.explicitReceiver
		}
	}
	fun FirReturnExpression.getYieldingReceiver(): FirExpression? {
		visitElement(this)
		return returnExpression
	}
}