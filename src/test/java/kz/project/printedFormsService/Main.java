package kz.project.printedFormsService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] bytes = Files.readAllBytes(Path.of("/home/bazarbay/IdeaProjects/printedFormsService/src/main/resources/Request.xml"));
        System.out.println(new String(bytes));
    }
}
