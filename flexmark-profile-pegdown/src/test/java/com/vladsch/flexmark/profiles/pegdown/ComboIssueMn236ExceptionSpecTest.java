package com.vladsch.flexmark.profiles.pegdown;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import com.vladsch.flexmark.util.data.DataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.runners.Parameterized;
import com.vladsch.flexmark.test.FlexmarkSpecExampleRenderer;
import com.vladsch.flexmark.test.SpecExampleRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vladsch.flexmark.profiles.pegdown.Extensions.*;

public class ComboIssueMn236ExceptionSpecTest extends ComboSpecTestCase {
    private static final String SPEC_RESOURCE = "/issue_mn_236_exception_spec.md";
    static final DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions((ALL & ~HARDWRAPS) | (ALL_OPTIONALS & ~(EXTANCHORLINKS | EXTANCHORLINKS_WRAP))).toMutable()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            .set(HtmlRenderer.FENCED_CODE_LANGUAGE_CLASS_PREFIX, "")
            .set(HtmlRenderer.OBFUSCATE_EMAIL_RANDOM, false)
            .set(HtmlRenderer.PERCENT_ENCODE_URLS, true);

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("hard-breaks", PegdownOptionsAdapter.flexmarkOptions((ALL & ~HARDWRAPS) | (ALL_OPTIONALS & ~(EXTANCHORLINKS | EXTANCHORLINKS_WRAP)) | HARDWRAPS)/*.toMutable().remove(Parser.EXTENSIONS)*/);
        optionsMap.put("anchor-links", PegdownOptionsAdapter.flexmarkOptions((ALL & ~HARDWRAPS) | (ALL_OPTIONALS & ~(EXTANCHORLINKS | EXTANCHORLINKS_WRAP)) | ANCHORLINKS)/*.toMutable().remove(Parser.EXTENSIONS)*/);
    }

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    private static DataHolder optionsSet(String optionSet) {
        return optionsMap.get(optionSet);
    }

    public ComboIssueMn236ExceptionSpecTest(SpecExample example) {
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
