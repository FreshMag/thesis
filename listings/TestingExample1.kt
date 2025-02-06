@OptIn(ExperimentalCompilerApi::class)
class TestAlignRawWarning : FreeSpec({
	"A single aggregate function called inside another one" - {
		val fileName = "TestAggregateInLoop.kt"
		val program = // get text from resource file...
		val sourceFile = SourceFile.kotlin(fileName, program)
		"should compile" - {
			val result = KotlinCompilation().apply {
				sources = listOf(sourceFile)
				compilerPluginRegistrars = listOf(AlignmentComponentRegistrar())
				inheritClassPath = true
			}.compile()
			val expectedWarningMessage = // warning to check...
			result.exitCode shouldBe KotlinCompilation.ExitCode.OK
			result.messages shouldContain expectedWarningMessage
		}
	}
})