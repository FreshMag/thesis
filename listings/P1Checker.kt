object ExplicitAlignDealign : FirFunctionCallChecker(MppCheckerKind.Common) {
    override fun check(
        expression: FirFunctionCall,
        context: CheckerContext,
        reporter: DiagnosticReporter,
    ) {
        val fqnCalleeName = expression.fqName()
        if (fqnCalleeName in FORBIDDEN_FUNCTIONS) {
            reporter.reportOn(
                expression.calleeReference.source,
                FirCollektiveErrors.FORBIDDEN_FUNCTION_CALL,
                fqnCalleeName,
                context,
            )
        }
    }
	// ... 
}