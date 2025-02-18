fun FirAnonymousFunctionExpression.extractReturnExpression(): FirExpression? =
	object : FirVisitorVoid() {
			private var returnExpression: FirExpression? = null
			// ...
			override fun visitReturnExpression(expression: FirReturnExpression) {
				returnExpression = expression.result
			}

		fun extractReturnExpression(): FirExpression? {
			visitElement(this@extractReturnExpression)
			return returnExpression
		}
	}.extractReturnExpression()