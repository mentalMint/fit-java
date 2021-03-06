package ru.nsu.fit.oop.lab2.factory.commands;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.nsu.fit.oop.lab2.exceptions.EmptyStackException;
import ru.nsu.fit.oop.lab2.factory.Calculator;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AddTest {
    @Test
    void execute() throws EmptyStackException {
        Calculator.ExecutionContext context = new Calculator.ExecutionContext();
        context.parameters = new HashMap<>();
        Stack<Double> stack = new Stack<>();
        stack.add(2.);
        stack.add(3.);
        context.stack = stack;
        Add add = new Add();

        add.execute(null, context);

        assertEquals(5., context.stack.peek());
    }

    @Test
    void failToExecuteWithNullArguments() {
        Calculator.ExecutionContext context = new Calculator.ExecutionContext();
        context.parameters = new HashMap<>();
        Stack<Double> stack = new Stack<>();
        context.stack = stack;
        Add add = new Add();

        Exception exception = assertThrows(Exception.class, () -> {
            add.execute(null, context);
        });

        String expectedMessage = "Add. Less then 2 numbers in the stack. Too few for Add.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}