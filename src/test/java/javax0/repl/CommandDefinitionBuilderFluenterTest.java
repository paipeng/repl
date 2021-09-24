package javax0.repl;

import javax0.geci.engine.Geci;
import javax0.geci.fluent.Fluent;
import javax0.geci.fluent.FluentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static javax0.geci.api.Source.maven;

public class CommandDefinitionBuilderFluenterTest {


    @Test
    @DisplayName("Fluent API for the CommandDefinitionBuilder is up-to-date")
    void generateFluentAPI4CommandDefinitionBuilder() throws Exception {
        Geci geci;
        Assertions.assertFalse(
            (geci = new Geci())
                .source(maven().mainSource()).register(new Fluent()).generate(),
            geci.failed()
        );
    }

    public static FluentBuilder sourceBuilderGrammar() {
        FluentBuilder klass = FluentBuilder.from(CommandDefinitionBuilder.class);
        return klass
            .one("kw")
            .optional(klass.oneOf(klass.one("noParameters"), klass.one("parameters"), klass.oneOrMore("parameter")))
            .zeroOrMore("regex")
            .one("usage")
            .one("help")
            .one("executor").name("CommandDefinitionBuilderReady")
            .one("build");
    }
}
