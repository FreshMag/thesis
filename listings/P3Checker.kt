object UnnecessaryYielding : FirFunctionCallChecker(MppCheckerKind.Common) {
	private val constructs = // Fully-qualified names of constructs using the YieldingContext

	private fun FirFunctionCall.usesAnUnnecessaryYieldingContext(): Boolean =
		with(YieldingUnnecessaryUsageVisitor()) {
			containsUnnecessaryYielding()
		}

	override fun check(
			expression: FirFunctionCall,
			context: CheckerContext,
			reporter: DiagnosticReporter,
	) {
		if (expression.fqName() in constructs 
					&& expression.usesAnUnnecessaryYieldingContext()) {
			// report as in the other checkers
		}
	}
}