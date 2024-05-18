package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class RunTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testValidInput() {
        String input = "Russia Moscow 5\nend\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Main.setScanner(new Scanner(in));
        Main.run();

        String output = outContent.toString();
        assertThat(output, containsString("Заказ доставки по адресу:"));
        assertThat(output, containsString("Страна: Russia"));
        assertThat(output, containsString("Город: Moscow"));
        assertThat(output, containsString("Вес посылки: 5,00 кг"));
        assertThat(output, containsString("Стоимость доставки: 6,00"));
    }

    @Test
    public void testInvalidAddress() {
        String input = "Country City 5\nend\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Main.setScanner(new Scanner(in));
        Main.run();

        String output = outContent.toString();
        assertTrue(output.contains("Доставка в указанный адрес не предоставляется."));
    }

    @Test
    public void testIncompleteAddress() {
        String input = "Russia Moscow\nend\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Main.setScanner(new Scanner(in));
        Main.run();

        String output = outContent.toString();
        assertTrue(output.contains("Недостаточно данных. Попробуйте снова."));
    }

    @Test
    public void testInvalidExit() {
        String input = "0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        Main.setScanner(new Scanner(in));
        Main.run();

        String output = outContent.toString();
        // Проверяем что программа завершилась корректно
        assertFalse(output.contains("Недостаточно данных. Попробуйте снова."));
    }
}