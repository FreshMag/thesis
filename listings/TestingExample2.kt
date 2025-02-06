"A single aggregate function called inside a loop" - {
	val testingProgramTemplate = 
		CompileUtils.testingProgramFromResource("TestAggregateInLoop.kt")

	"without a specific alignedOn" - {
		val testingProgram = testingProgramTemplate.formatCode("", "", "", "")
		"should produce a warning" - {
				testingProgram shouldCompileWith warning(EXPECTED_WARNING_MESSAGE)
		}
	}
	"with a specific alignedOn" - {
		val testingProgram = 
			testingProgramTemplate.formatCode("", "alignedOn(0) {", "}", "")
		"should compile without any warning" - {
				testingProgram shouldCompileWith noWarning
		}
	}
	// ...
}
