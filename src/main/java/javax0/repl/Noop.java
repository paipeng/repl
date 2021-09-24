package javax0.repl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static javax0.repl.CommandDefinitionBuilder.start;

/**
 * A sample application that defines no real commands at all. This is to manually test the Repl, especially command
 * completion.
 */
public class Noop {
    public static void main(String[] args) {
        new Noop().noop();
    }

    private void noop() {
        final Repl sut = new Repl();
        sut.title("Noop REPL Application to end-to-end manual test the application")
            .prompt("REPL > $ ")
            .debug()
            .command(
                start()
                    .kw("first")
                    .parameters(new HashSet<>(Arrays.asList("f1", "f2")))
                    .usage("")
                    .help("")
                    .executor(this::noop))
            .command(
                start()
                    .kw("second")
                    .parameters(new HashSet<>(Arrays.asList("s1", "s2")))
                    .usage("")
                    .help("")
                    .executor(this::noop))
            .command(
                start()
                    .kw("third")
                    .parameters(new HashSet<>(Arrays.asList("t1", "t2")))
                    .usage("")
                    .help("")
                    .executor(this::noop))
            .alias("3rd", "third")
            .run()
        ;
    }

    private void noop(CommandEnvironment env) {
        env.message().info(env.line());
    }
}
