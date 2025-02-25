val testingProgramTemplate = // like before
listOf(
	"exampleAggregate" to "exampleAggregate()",
	"neighboring" to "neighboring(0)",
).forEach { (functionName, functionCall) ->
	"using $functionName without a specific alignedOn" - {
		"should produce a warning" - {
			val testingProgram = testingProgramTemplate
					.put("aggregate", functionCall)
			testingProgram shouldCompileWith warning(EXPECTED_WARNING_MESSAGE.format(functionName))
		}
	}
	"using $functionName wrapped in a specific alignedOn" - {
		val testingProgram = testingProgramTemplate
			.put("beforeAggregate", "alignedOn(0) {")
			.put("afterAggregate", "}")
		"should compile without any warning" - {
			testingProgram shouldCompileWith noWarning
		}
	}
}