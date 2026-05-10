package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        String inputFile = null;
        String outputDir = null;

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {

                case "--input":
                    inputFile = args[++i];
                    break;

                case "--output":
                    outputDir = args[++i];
                    break;

                default:
                    System.out.println(
                            "Unknown argument: " + args[i]
                    );
            }
        }

        System.out.println("Input file: " + inputFile);
        System.out.println("Output dir: " + outputDir);

        ExcelUtils.processCsv(inputFile, outputDir);

    }
}