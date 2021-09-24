package javax0.sample;

import javax0.repl.CommandEnvironment;
import javax0.repl.Repl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static javax0.repl.CommandDefinitionBuilder.start;

class ReplTestApplicationTest {

    public static void main(String[] args) {
        new ReplTestApplicationTest().test();
    }

    private Repl sut;

    private void test() {
        sut = new Repl();
        sut.command(
                start().
                        kw("echo")
                        .usage("echo parameters")
                        .help("Use echo to print out to the console the parameters that are given on the line")
                        .executor(this::echoCommand)
        ).alias("e", "echo")
                .command(
                        start()
                                .kw("return")
                                .parameter("output").parameter("delayed").parameter("text")
                                .usage("return value")
                                .help("Use return to calculate a value and return it to the console.")
                                .executor(this::returnCommand)
                ).alias("ret", "return").alias("a", "alias")
                .command(start().
                        kw("abs")
                        .regex("canonical", "(\\d+)\\s*\\+(\\d+)i")
                        .regex("polar", "(\\d+)\\((\\d+\\.?\\d*)\\)")
                        .usage("abs complexnumber")
                        .help("Print out the absolut value of a complex number\n" +
                                "You can specify the complex number in a+bi format or\n" +
                                "R(rad) format.")
                        .executor(this::absCommand)
                )
                .command(start().kw("alias").usage("").help("").executor(this::myAlias))
                .title("Sample REPL Application to end-to-end manual test the application")
                .prompt("REPL > $ ")
                .stateReporter(this::report)
                .debug()
                .run()
        ;
    }

    @SuppressWarnings("EmptyMethod")
    private void report(CommandEnvironment env) {

    }


    private void absCommand(CommandEnvironment env) {
        if (env.matcherId().equals("polar")) {
            final int abs = Integer.parseInt(env.matcher().group(1));
            env.message().info("" + abs);
        } else {
            final int real = Integer.parseInt(env.matcher().group(1));
            final int imag = Integer.parseInt(env.matcher().group(2));
            final double abs = Math.sqrt(real * real + imag * imag);
            env.message().info("" + abs);
        }
    }

    private void myAlias(CommandEnvironment env) {
        env.console().writer().print("This is my alias!!!\n");
        env.console().writer().flush();
        final String alias = env.parser().get(0).orElse(null);
        final String command = env.parser().get(1).orElse(null);
        sut.alias(alias, command);
        env.message().info(alias + " was really set to alias " + command);
    }

    private void returnCommand(CommandEnvironment env) {
        final Optional<String> delay = env.parser().get("delayed");
        if (delay.isPresent()) {
            try {
                Thread.sleep(Integer.parseInt(delay.get()));
            } catch (InterruptedException ignored) {
            }
        }
        final Optional<String> output = env.parser().get("output", new HashSet<>(Arrays.asList("yes", "no")));
        if (output.isPresent() && output.get().equals("yes")) {
            final String text = env.parser().getOrDefault("text", "");
            env.message().info(text);
        }
    }

    private void echoCommand(CommandEnvironment env) {
        for (int i = 0; env.parser().get(i).isPresent(); i++) {
            env.message().info(env.parser().get(i).orElse(""));
        }
    }

}
