class ConstructCallVisitor : FirVisitorVoid() {
	private var checkedParametersDeclarations = listOf<FirValueParameterSymbol>()
	private val found = true

	override fun visitAnonymousFunctionExpression(anonymousFunctionExpression: FirAnonymousFunctionExpression) {
		if (!nestedAnonymousFunction) { // we don't check the parameters of nested anonymous functions
			val anonymousFunction = anonymousFunctionExpression.anonymousFunction
			val parameters = anonymousFunction.valueParameters // usage of FIR API
			checkedParametersDeclarations = parameters.map { it.symbol } // we save the symbols to check
			if (checkedParametersDeclarations.isEmpty()) { 
				found = false // no parameters -> skip check
				return
			}
			nestedAnonymousFunction = true
		}
		super.visitAnonymousFunctionExpression(anonymousFunctionExpression)
	}

	// Visits (resolved) name references in the code (variables usage for example)
	override fun visitResolvedNamedReference(resolvedNamedReference: FirResolvedNamedReference) {
		if (resolvedNamedReference.resolvedSymbol in checkedParametersDeclarations) {
			checkedParametersDeclarations = // we filter out the parameter that are used
				checkedParametersDeclarations.filter { it != resolvedNamedReference.resolvedSymbol }
			if (checkedParametersDeclarations.isEmpty()) {
				found = false // if all the parameters have been used, we are done
			}
		}
	}
}
