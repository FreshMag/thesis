fun CheckerContext.isInsideAggregateFunction(): Boolean =
    containingElements.any { (it as? FirSimpleFunction)?.receiverParameter?.isAggregate(session) == true }

fun FirReceiverParameter.isAggregate(session: FirSession): Boolean =
    typeRef.toClassLikeSymbol(session)?.name?.asString() == AGGREGATE_CLASS_NAME

fun FirFunctionCall.isAggregate(session: FirSession): Boolean {
    val callableSymbol = toResolvedCallableSymbol()
    return callableSymbol?.receiverParameter?.isAggregate(session) == true ||
        callableSymbol?.getContainingClassSymbol()?.name?.asString() == AGGREGATE_CLASS_NAME
}