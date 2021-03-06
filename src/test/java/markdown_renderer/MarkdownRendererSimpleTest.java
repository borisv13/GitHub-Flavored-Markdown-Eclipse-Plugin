package markdown_renderer;

import org.junit.Test;

public class MarkdownRendererSimpleTest extends MarkdownRendererAbstractTest {

	@Test
	public void italics() {
		readAndAssertFileContents("italics.html", "*test*");
	}

	@Test
	public void bold() {
		readAndAssertFileContents("bold.html", "**test**");
	}

	@Test
	public void single_paragraph() {
		readAndAssertFileContents("single-paragraph.html", "test\ntest");
	}

	@Test
	public void two_paragraphs() {
		readAndAssertFileContents("two-paragraphs.html", "test\n\ntest");
	}

	@Test
	public void indented_code_block() {
		readAndAssertFileContents("indented-code-block-e76.html", "    a simple\n      indented code block");
	}

	@Test
	public void indented_code_block_first_line() {
		readAndAssertFileContents("indented-code-block-e85.html", "        foo\n    bar");
	}

	@Test
	public void simple_table() {
		readAndAssertFileContents("simple-table.html",
				"| First Header  | Second Header |\n" + "| ------------- | ------------- |\n"
						+ "| Content Cell  | Content Cell  |\n" + "| Content Cell  | Content Cell  |");
	}

	@Test
	public void align_attributed_table() {
		readAndAssertFileContents("align-attributed-table.html",
				"| Left-aligned | Center-aligned | Right-aligned |\n"
						+ "| :---         |     :---:      |          ---: |\n"
						+ "| git status   | git status     | git status    |\n"
						+ "| git diff     | git diff       | git diff      |");
	}

	@Test
	public void unordered_list() {
		readAndAssertFileContents("unorderedList.html", "* test\n* test\n* test\n");
	}

	@Test
	public void horizontal_rule() {
		readAndAssertFileContents("horizontalRule.html", "***");
	}

	@Test
	public void code() {
		readAndAssertFileContents("code.html", "`test`");
	}

	@Test
	public void header1() {
		readAndAssertFileContents("header1.html", "# H1 Test");
	}

	@Test
	public void header2() {
		readAndAssertFileContents("header2.html", "## H2 Test");
	}

	@Test
	public void header3() {
		readAndAssertFileContents("header3.html", "### H3 Test");
	}

	@Test
	public void header4() {
		readAndAssertFileContents("header4.html", "#### H4 Test");
	}

	@Test
	public void header5() {
		readAndAssertFileContents("header5.html", "##### H5 Test");
	}

	@Test
	public void header6() {
		readAndAssertFileContents("header6.html", "###### H6 Test");
	}

	@Test
	public void ordered_list() {
		readAndAssertFileContents("orderedList.html", "1. test\n1. test\n1. test\n");
	}

//	TODO add CSS of gfm blockquote
//	.markdown-body blockquote {
//		padding: 0 1em;
//		color: #6a737d;
//    	border-left: 0.25em solid #dfe2e5;
//	}

	@Test
	public void blockquote() {
		readAndAssertFileContents("blockquote.html", "> test");
	}

	@Test
	public void backticks() {
		readAndAssertFileContents("backticks.html", "```\n<\n >\n```");
	}

	@Test
	public void tildes() {
		readAndAssertFileContents("tildes.html", "~~~\n<\n >\n~~~");
	}

	@Test
	public void not_enough_backticks() {
		readAndAssertFileContents("notEnoughBackticks.html", "``\nfoo\n``");
	}

	@Test
	public void unclosed_blocks() {
		readAndAssertFileContents("unclosedBlocks.html", "> ```\n> aaa\n\nbbb");
	}

	// Four spaces indentation produces an indented code block
	@Test
	public void four_spaces_indentation() {
		readAndAssertFileContents("fourSpacesIndentation.html", "    ```\n    aaa\n    ```");
	}

	// Closing fences may be indented by 0-3 spaces, and their indentation need not
	// match that of the opening fence
	@Test
	public void closing_fence_intended_and_need_not_match_opening_fence() {
		readAndAssertFileContents("indentedClosingFence.html", "   ```\naaa\n  ```");
	}

	// Code fences (opening and closing) cannot contain internal spaces
	@Test
	public void code_fence_cannot_contain_internal_spaces() {
		readAndAssertFileContents("containingSpaces.html", "``` ```\naaa");
	}

	// Closing code fences cannot have info strings
	@Test
	public void closing_fence_cannot_contain_strings() {
		readAndAssertFileContents("closingFenceContainStrings.html", "```\n``` aaa\n```");
	}

	@Test
	public void strikethrough() {
		readAndAssertFileContents("strikethrough.html", "~~test~~");
	}

	@Test
	public void standard_image_1() {
		readAndAssertFileContents("standardImage1.html", "![foo](/url \"title\")");
	}

	@Test
	public void standard_image_2() {
		readAndAssertFileContents("standardImage2.html",
				"![foo *bar*]\n" + "\n" + "[foo *bar*]: train.jpg \"train & tracks\"");
	}

	@Test
	public void standard_image_3() {
		readAndAssertFileContents("standardImage3.html", "![foo ![bar](/url)](/url2)");
	}

	@Test
	public void standard_image_4() {
		readAndAssertFileContents("standardImage3.html", "![foo [bar](/url)](/url2)");
	}

	@Test
	public void reference_style() {
		readAndAssertFileContents("referenceStyleImage.html", "![foo][bar]\n" + "\n" + "[bar]: /url");
	}

	@Test
	public void collapsed() {
		readAndAssertFileContents("collapsedImage.html", "![*foo* bar][]\n" + "\n" + "[*foo* bar]: /url \"title\"");
	}

	@Test
	public void shortcut1() {
		readAndAssertFileContents("standardImage1.html", "![foo]\n" + "\n" + "[foo]: /url \"title\"");
	}

	@Test
	public void shortcut2() {
		readAndAssertFileContents("collapsedImage.html", "![*foo* bar]\n" + "\n" + "[*foo* bar]: /url \"title\"");
	}

	@Test
	public void case_insensitive() {
		readAndAssertFileContents("caseInsensitive.html", "![Foo]\n" + "\n" + "[foo]: /url \"title\"");
	}

	@Test
	public void hmtl_table() {
		readAndAssertFileContents("htmlTable.html", "<table>\n" + "  <tr>\n" + "    <td>\n" + "           hi\n"
				+ "    </td>\n" + "  </tr>\n" + "</table>\n" + "\n" + "okay.");
	}

	@Test
	public void partial_tag_for_firstline() {
		readAndAssertFileContents("partialTagForFirstline.html", "<div id=\"foo\"\n" + "  class=\"bar\">\n" + "</div>");
	}

	@Test
	public void pre_tag() {
		readAndAssertFileContents("preTag.html", "<pre language=\"haskell\"><code>\n" + "import Text.HTML.TagSoup\n"
				+ "\n" + "main :: IO ()\n" + "main = print $ parseTags tags\n" + "</code></pre>\n" + "okay");
	}

	@Test
	public void script_tag() {
		readAndAssertFileContents("scriptTag.html",
				"<script type=\"text/javascript\">\n" + "// JavaScript example\n" + "\n"
						+ "document.getElementById(\"demo\").innerHTML = \"Hello JavaScript!\";\n" + "</script>\n"
						+ "okay");
	}

	@Test
	public void style_tag() {
		readAndAssertFileContents("styleTag.html", "<style\n" + "  type=\"text/css\">\n" + "h1 {color:red;}\n" + "\n"
				+ "p {color:blue;}\n" + "</style>\n" + "okay");
	}

	// garbage in, garbage out
	@Test
	public void uncomplete_tag() {
		readAndAssertFileContents("uncompleteTag.html", "<div id=\"foo\"\n" + "*hi*");
	}

	// anything on the last line after the end tag will be included in the
	// HTML block:
	@Test
	public void anything_on_last_line_after_end_tag_will_be_included_in_HTML() {
		readAndAssertFileContents("anythingOnLastLineAfterEndTag.html", "<script>\n" + "foo\n" + "</script>1. *bar*");
	}

	@Test
	public void CDATA() {
		readAndAssertFileContents("CDATA.html",
				"<![CDATA[\n" + "function matchwo(a,b)\n" + "{\n" + "  if (a < b && a < 0) then {\n" + "    return 1;\n"
						+ "\n" + "  } else {\n" + "\n" + "    return 0;\n" + "  }\n" + "}\n" + "]]>\n" + "okay");
	}

	// The opening tag can be indented 1-3 spaces, but not 4
	@Test
	public void indented_opening_tag() {
		readAndAssertFileContents("indentedOpeningTag.html", "  <div>\n" + "\n" + "    <div>");
	}

	@Test
	public void block_cannot_interrupt_paragraph() {
		readAndAssertFileContents("cannotInterruptParagraph.html", "Foo\n" + "<a href=\"bar\">\n" + "baz");
	}

	@Test
	public void no_spaces_after_block_quote() {
		readAndAssertFileContents("noSpacesAfterBlockQuote.html", "># Foo\n>bar\n> baz");
	}

	@Test
	public void indented_one_to_three_spaces_block_quote() {
		readAndAssertFileContents("indentedOneToThreeSpacesBlockQuote.html", "   > # Foo\n   > bar\n > baz");
	}

	@Test
	public void omit_block_quote_before_paragraph_continuation_text() {
		readAndAssertFileContents("omitBlockQuoteBeforeParagraph.html", "> # Foo\n> bar\nbaz");
	}

	@Test
	public void contain_some_lazy_and_non_lazy_continuation_line() {
		readAndAssertFileContents("containLazyAndNonLazyLine.html", "> bar\nbaz\n> foo");
	}

	@Test
	public void cannot_omit_block_quote_before_indented_code_bloack() {
		readAndAssertFileContents("omitBlockQuoteBeforeIndentedCodeBlock.html", ">     foo\n    bar");
	}

	@Test
	public void cannot_omit_block_quote_before_fenced_code_bloack() {
		readAndAssertFileContents("omitBlockQuoteBeforeFencedCodeBlock.html", "> ```\nfoo\n```");
	}

	@Test
	public void two_paragraph_block_quote() {
		readAndAssertFileContents("twoParagraphBlockQuote.html", "> foo\n>\n> bar");
	}

	@Test
	public void links_proper() {
		readAndAssertFileContents("link-proper.html", "[link](/uri)");
	}

	@Test
	public void links_improper_space() {
		readAndAssertFileContents("link-improper-space.html", "[link] (/uri)");
	}

	@Test
	public void hard_line_break() {
		readAndAssertFileContents("hard_line_break.html", "foo\\\r\nbar");
  }
  
  @Test
	public void escape_char() {
		readAndAssertFileContents("escape_char.html", "\\# hi there");
	}

}
