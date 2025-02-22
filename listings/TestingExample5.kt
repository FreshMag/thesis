val testSubjects =
	subjekt { 
		addSource("src/test/resources/subjekt/IterationWithAggregate.yaml")
	}.toTempFiles() // creates a map of names -> temporary source files

forAll(testedAggregateFunctions) { functionCall ->
	forAll(formsOfIteration) { iteration, iterationDescription ->
		"using $functionCall in $iterationDescription without alignedOn" - {
			// uses a utility function to retrieve the source from `testSubjects'
			val code = getProgramFromCase("Iteration") 

			"should compile producing a warning" - {
				code shouldCompileWith
					warning(
							expectedWarning(functionName),
					)
			}
		}
	// ...