package com.vladsch.flexmark.superscript;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import com.vladsch.flexmark.test.FlexmarkSpecExampleRenderer;
import com.vladsch.flexmark.test.SpecExampleRenderer;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboSuperscriptSpecTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/superscript_ast_spec.md";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            //.set(HtmlRenderer.PERCENT_ENCODE_URLS, true)
            .set(Parser.EXTENSIONS, Collections.singleton(SuperscriptExtension.create()));

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("src-pos", new MutableDataSet().set(HtmlRenderer.SOURCE_POSITION_ATTRIBUTE, "md-pos"));
        optionsMap.put("style-superscript", new MutableDataSet().set(SuperscriptExtension.SUPERSCRIPT_STYLE_HTML_OPEN, "<span class=\"text-sup\">").set(SuperscriptExtension.SUPERSCRIPT_STYLE_HTML_CLOSE, "</span>"));
        // optionsMap.put("option1", new MutableDataSet().set(SuperscriptExtension.SUPERSCRIPT_OPTION1, true));
    }

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    private static DataHolder optionsSet(String optionSet) {
        if (optionSet == null) return null;
        return optionsMap.get(optionSet);
    }

    public ComboSuperscriptSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @Nullable
    @Override
    public DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @NotNull
    @Override
    public String getSpecResourceName() {
        return SPEC_RESOURCE;
    }


    @Override
    public @NotNull SpecExampleRenderer getSpecExampleRenderer(@Nullable DataHolder exampleOptions) {
        return new FlexmarkSpecExampleRenderer(exampleOptions, PARSER, RENDERER, true);
    }
}
