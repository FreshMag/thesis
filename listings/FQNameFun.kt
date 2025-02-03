/**
	* Returns the fully qualified name of this [FirFunctionCall].
	*/
fun FirFunctionCall.fqName(): String {
		val callableId = calleeReference.toResolvedFunctionSymbol()?.callableId

		val packageName = callableId?.packageName?.asString()
		val className = callableId?.className?.asString()
		val functionName = callableId?.callableName?.asString()
		return if (className != null) {
				"$packageName.$className.$functionName"
		} else {
				"$packageName.$functionName"
		}
}