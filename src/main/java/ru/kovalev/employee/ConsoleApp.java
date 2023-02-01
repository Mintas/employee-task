package ru.kovalev.employee;

import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kovalev.employee.service.InputHandler;

@RequiredArgsConstructor
@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {
    private static final String EXIT_COMMAND = "exit";
    private static final String COMMAND_LIST = """
               List of commands supported:
                 "exit" -- will stop the application;
                 "id" -- will show the information about employee; example input: 4; example output:
                    name: John Doe Jr
                    supervisor: John Doe Sr
                    subordinates: Biba Bro, Boba Mate, Cute Dude
            """;
    private final InputHandler inputHandler;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner in = new Scanner(System.in);

        afterInitialize();

        while (in.hasNext()) {
            String input = in.nextLine();
            if (EXIT_COMMAND.equalsIgnoreCase(input)) {
                break;
            }
            handleInput(input);
        }

        beforeShutdown();
    }

    private void afterInitialize() {
        System.out.println("Hello there!\n" + COMMAND_LIST);
    }

    private void handleInput(String input) {
        boolean succeed = inputHandler.handeInput(input);
        if (!succeed) {
            System.err.printf("Unrecognized input: \"%s\"%n", input);
            System.out.println(COMMAND_LIST);
        } else {
            System.out.println("Enter next command =)");
        }
    }

    private void beforeShutdown() {
        System.out.println("Looks like we have finished there! \n Shutting down application! \n Have a nice day!");
    }
}
