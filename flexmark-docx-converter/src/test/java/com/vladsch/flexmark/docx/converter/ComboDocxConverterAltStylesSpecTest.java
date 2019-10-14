package com.vladsch.flexmark.docx.converter;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.List;

public class ComboDocxConverterAltStylesSpecTest extends ComboDocxConverterSpecTestBase {
    private static final String SPEC_RESOURCE = "/docx_converter_ast_alt_styles_spec.md";
    static final String FILE_TEST_CASE_DUMP_LOCATION = "/flexmark-docx-converter/src/test/resources/docx_converter_ast_alt_styles_spec/";
    public static final String TEMPLATE_XML = "/empty-numbered-headings.xml";

    private static final DataHolder OPTIONS = new MutableDataSet(ComboDocxConverterSpecTestBase.OPTIONS)
            .set(DocxRenderer.DEFAULT_TEMPLATE_RESOURCE, TEMPLATE_XML);

    private static final Parser PARSER = Parser.builder(OPTIONS).build();
    private static final DocxRenderer RENDERER = DocxRenderer.builder(OPTIONS).build();

    public ComboDocxConverterAltStylesSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        return getTestData(SPEC_RESOURCE);
    }

    @NotNull
    @Override
    public String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public Parser parser() {
        return PARSER;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public DocxRenderer renderer() {
        return RENDERER;
    }

    @Override
    public String getProjectRootDirectory() {
        return PROJECT_ROOT_DIRECTORY;
    }

    @Override
    public String getFileTestCaseDumpLocation() {
        return FILE_TEST_CASE_DUMP_LOCATION;
    }
}
