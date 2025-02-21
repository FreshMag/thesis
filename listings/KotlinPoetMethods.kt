fun FunSpec.Builder.block(
	header: String = "",
	content: FunSpec.Builder.() -> FunSpec.Builder,
): FunSpec.Builder =
	beginControlFlow(header)
		.content()
		.endControlFlow()

fun FunSpec.Builder.alignedOn(content: FunSpec.Builder.() -> FunSpec.Builder): FunSpec.Builder =
	block("alignedOn(0)", content)