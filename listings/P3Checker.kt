object UnnecessaryYielding : FirFunctionCallChecker(MppCheckerKind.Common) {

	private fun FirFunctionCall.usesAnUnnecessaryYieldingContext(): Boolean =
		with(YieldingUnnecessaryUsageVisitor()) {
			containsUnnecessaryYielding()
		}

	override fun check(
		expression: FirFunctionCall,
		context: CheckerContext,
		reporter: DiagnosticReporter,
	) {
		if (expression.fqName() in constructs // FQ names of the yielding operations
					&& expression.usesAnUnnecessaryYieldingContext()) {
			// report as in the other checkers
		}
	}
}