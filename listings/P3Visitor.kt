class YieldingUnnecessaryUsageVisitor : FirVisitorVoid() {
  // ...private fields declarations...

  override fun visitFunctionCall(functionCall: FirFunctionCall) {
    if (functionCall.fqName() == YIELDING_FUNCTION_FQ_NAME) {
      insideYielding = true
      yieldingReceiver = functionCall.explicitReceiver // we save the receiver
      functionCall.argumentList.arguments.forEach(::visitElement) // we visit the arguments
      insideYielding = false
      return
    }
    super.visitFunctionCall(functionCall)
  }

  override fun visitReturnExpression(returnExpression: FirReturnExpression) {
    if (insideYielding) { // inside the anonymous function passed to yielding
      containsUnnecessaryYielding = returnExpression
        .result
        .isStructurallyEquivalentTo(yieldingReceiver) // we check if the return value is the receiver
      return
    }
    super.visitReturnExpression(returnExpression)
  }
  // ...
}