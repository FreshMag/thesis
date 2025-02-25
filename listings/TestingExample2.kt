"A single aggregate function called inside a loop" - {
	val testingProgramTemplate = 
		CompileUtils.testingProgramFromResource("TestAggregateInLoop.kt")

	"without a specific alignedOn" - {
		val program = testingProgramTemplate.formatCode("", "", "", "")
		"should produce a warning" - {
				program shouldCompileWith warning(EXPECTED_WARNING_MESSAGE)
		}
	}
	"with a specific alignedOn" - {
		val program = testingProgramTemplate.formatCode("", "alignedOn(0) {", "}", "")
		"should compile without any warning" - {
				program shouldCompileWith noWarning
		}
	}
}
