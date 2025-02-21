// creates a file with the needed imports and an example Aggregate function
val sourceFile = simpleTestingFileWithAggregate() 
// create a function with `Aggregate` receiver of type Int
val startingFunction = simpleAggregateFunction(INT) 

forAll(testedAggregateFunctions) { functionCall ->

	"using $functionCall wrapped in a specific alignedOn" - {
		val generated =
			startingFunction + { 	// customization of a common function
				loop {
					alignedOnS {
						functionCall
					}
				}
			}
		"should compile without any warning" - {
			sourceFile withFunction generated shouldCompileWith noWarning
		}
	}
// ...
