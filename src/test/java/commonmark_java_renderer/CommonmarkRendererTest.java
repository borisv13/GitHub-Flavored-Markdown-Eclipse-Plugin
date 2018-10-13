package commonmark_java_renderer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommonmarkRendererTest {

	@Test
	public void italics() {
		CommonmarkRenderer cr = new CommonmarkRenderer();
		assertEquals("<p><em>test</em></p>\n", cr.render("*test*"));
	}

}
