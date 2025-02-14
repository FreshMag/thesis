class ConstructCallVisitor : FirVisitorVoid() {
	private var checkedParametersDeclarations = listOf<FirValueParameterSymbol>()
	private var found = true
	private var nestedAnonymousFunction = false
	// ...

	override fun visitAnonymousFunctionExpression(anonymousFunctionExpression: FirAnonymousFunctionExpression) {
		if (!nestedAnonymousFunction) { // we don't want to check the parameters of the nested anonymous functions
			found = false
			val anonymousFunction = anonymousFunctionExpression.anonymousFunction
			val parameters = anonymousFunction.valueParameters
			checkedParametersDeclarations = parameters.map { it.symbol } // we save the symbols to check
			if (checkedParametersDeclarations.isEmpty()) { 
				found = true // if there are no parameters, we don't need to check them
				return
			}
			nestedAnonymousFunction = true
		}
		super.visitAnonymousFunctionExpression(anonymousFunctionExpression)
	}

	// This visits any (resolved) name reference in the code (variables usage for example)
	override fun visitResolvedNamedReference(resolvedNamedReference: FirResolvedNamedReference) {
		if (resolvedNamedReference.resolvedSymbol in checkedParametersDeclarations) {
			checkedParametersDeclarations = // we filter out the parameter that are used
				checkedParametersDeclarations.filter { it != resolvedNamedReference.resolvedSymbol }
			if (checkedParametersDeclarations.isEmpty()) {
				found = true // if all the parameters have been used, we are done
			}
		}
	}
	// ...
}
