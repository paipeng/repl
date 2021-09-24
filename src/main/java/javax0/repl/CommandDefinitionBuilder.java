package javax0.repl;

//import javax0.geci.annotations.Geci;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * A command definition builder that can be used to create a command definition. This is  the builder to be used to
 * create the argument to the {@link Repl#command(CommandDefinitionBuilderReady)} method.
 */
//@Geci("fluent definedBy='javax0.repl.CommandDefinitionBuilderFluenterTest::sourceBuilderGrammar'")
public class CommandDefinitionBuilder {
    private String keyword;
    private Set<String> parameters;
    private Consumer<CommandEnvironment> executor;
    private Map<String, Pattern> regexes;
    private String usage;
    private String help;

    private void kw(String keyword) {
        this.keyword = keyword;
    }

    private CommandDefinition build() {
        return new CommandDefinition(keyword, parameters, executor, regexes, usage, help);
    }

    private void executor(Consumer<CommandEnvironment> executor) {
        this.executor = executor;
    }

    private void usage(String usage) {
        this.usage = usage;
    }

    private void help(String help) {
        this.help = help;
    }

    private void parameters(Set<String> parameters) {
        if (this.parameters == null) {
            this.parameters = new HashSet<>(parameters);
        } else {
            this.parameters.addAll(parameters);
        }
    }

    private void noParameters() {
        if (parameters == null) {
            this.parameters = new HashSet<>(Arrays.asList());
        } else {
            throw new IllegalArgumentException(
                "You cannot define parameters and noParameters for the same command");
        }
    }

    private void parameter(String parameter) {
        if (parameters == null) {
            this.parameters = new HashSet<>(Arrays.asList(parameter));
        } else {
            this.parameters.add(parameter);
        }
    }

    private void regex(String name, String regex) {
        if (regexes == null) {
            this.regexes = new HashMap<>();
        }
        regexes.put(name, Pattern.compile(regex));
    }

    //<editor-fold id="fluent" desc="fluent API interfaces and classes">
    public static Ofob start(){
        return new Wrapper();
    }
    public static class Wrapper implements Abok,Efeh,Edak,CommandDefinitionBuilderReady,Acuh,Aduf,Ohug,Ofob,Ukeg,Ujaj,Ogoj,Uhab{
        private final javax0.repl.CommandDefinitionBuilder that;
        public Wrapper(){
            this.that = new javax0.repl.CommandDefinitionBuilder();
        }
        public Wrapper usage(String arg1){
            that.usage(arg1);
            return this;
        }
        public Wrapper help(String arg1){
            that.help(arg1);
            return this;
        }
        public Wrapper noParameters(){
            that.noParameters();
            return this;
        }
        public javax0.repl.CommandDefinition build(){
            return that.build();
        }
        public Wrapper kw(String arg1){
            that.kw(arg1);
            return this;
        }
        public Wrapper executor(java.util.function.Consumer<javax0.repl.CommandEnvironment> arg1){
            that.executor(arg1);
            return this;
        }
        public Wrapper regex(String arg1, String arg2){
            that.regex(arg1,arg2);
            return this;
        }
        public Wrapper parameters(java.util.Set<String> arg1){
            that.parameters(arg1);
            return this;
        }
        public Wrapper parameter(String arg1){
            that.parameter(arg1);
            return this;
        }
    }
    public interface CommandDefinitionBuilderReady {
        javax0.repl.CommandDefinition build();
    }
    public interface Aduf {
        CommandDefinitionBuilderReady executor(java.util.function.Consumer<javax0.repl.CommandEnvironment> arg1);
    }
    public interface Ukeg {
        Aduf help(String arg1);
    }
    public interface Ohug {
        Ukeg usage(String arg1);
    }
    public interface Efeh extends Ohug {
        Efeh regex(String arg1, String arg2);
    }
    public interface Ujaj {
        Efeh noParameters();
    }
    public interface Ogoj {
        Efeh parameters(java.util.Set<String> arg1);
    }
    public interface Edak extends Efeh {
        Edak parameter(String arg1);
    }
    public interface Abok {
        Edak parameter(String arg1);
    }
    public interface Uhab extends Abok,Ujaj,Ogoj{
    }
    public interface Acuh extends Efeh,Uhab {}
    public interface Ofob {
        Acuh kw(String arg1);
    }

    //</editor-fold>
}
