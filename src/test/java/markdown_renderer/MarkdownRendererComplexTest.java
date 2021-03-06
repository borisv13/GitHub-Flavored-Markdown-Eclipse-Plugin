package markdown_renderer;

import org.junit.Test;

public class MarkdownRendererComplexTest extends MarkdownRendererAbstractTest {

	@Test
	public void combine_excess_italics_with_bold() {
		readAndAssertFileContents("edgeCase_CombineExcessItalicsWithBold.html", "__***_**Test**_***__");
	}

	@Test
	public void seven_pound_symbols() {
		readAndAssertFileContents("edgeCase_SevenPoundSymbols.html", "####### Test");
	}

	@Test
	public void combine_italics_with_bold() {
		readAndAssertFileContents("edgeCase_CombineItalicsWithBold.html", "_**Test**_");
	}

	@Test
	public void combine_unordered_ordered_list() {
		readAndAssertFileContents("edgeCase_CombineUnorderedOrderedList.html", "1. * Test");
	}

	@Test
	public void triple_bold() {
		readAndAssertFileContents("edgeCase_TripleBold.html", "******Strike******");
	}

	@Test
	public void combine_blockquote_with_emphasis() {
		readAndAssertFileContents("edgeCase_CombineBlockquoteWithEmphasis.html", "> *text*");
	}

	@Test
	public void emphasis_plus_inline_code() {
		readAndAssertFileContents("edgeCase_EmphasisPlusInlineCode.html", "*`text`*");
	}

	@Test
	public void combine_unordered_list_bold_strikethrough() {
		readAndAssertFileContents("edgeCase_UnorderedListBoldStrikethrough.html", "* **~~Strike~~**");
	}

	@Test
	public void triple_italics() {
		readAndAssertFileContents("edgeCase_TripleItalics.html", "_*_Strike_*_");
	}

	@Test
	public void emphasis_mismatch() {
		readAndAssertFileContents("edgeCase_EmphasisMismatch.html", "_*___Test*__**");
	}

	@Test
	public void combine_triple_blockquote_double_inline_code() {
		readAndAssertFileContents("edgeCase_TripleBlockquote_DoubleInlineCode.html", ">>> ``Hi``");
	}

}