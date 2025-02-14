object UnnecessaryUseOfConstructs : FirFunctionCallChecker(MppCheckerKind.Common) {
	// ...

	private fun FirFunctionCall.doesNotUseParameter(): Boolean =
		if (/* construct is one of `neighboring` or `neighboringViaExchange` */) {
			with(EmptyReturnVisitor()) {
					hasEmptyReturn()
			}
		} else {
			with(ConstructCallVisitor()) {
					doesNotContainValueParameterUsagesInAnonymousFunctionCall()
			}
		}
	// since the `check` method is trivial, it is omitted in this case
}
