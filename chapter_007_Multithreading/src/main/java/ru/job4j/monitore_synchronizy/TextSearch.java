package ru.job4j.monitore_synchronizy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * TextSearch.
 *
 * @author Ayuzyak
 * @version 1.0
 * @since 10.09.2017
 */
public class TextSearch {
    /**
     * List of all files in directories and subdirectories.
     */
    private ArrayList<Path> files = new ArrayList<>();

    /**
     * Flag for stop searching.
     */
    private boolean fileIsFinding = false;

    /**
     * Search text in files.
     *
     * @param startPath path for passing.
     * @param text      for searching.
     * @return finding path or null.
     */
    public Path searchPath(Path startPath, String text) {
        SimpleFileVisitor<Path> visitor = new MyVisitor();
        try {
            Files.walkFileTree(startPath, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExecutorService executor = Executors.newCachedThreadPool();
        Path findPath = null;
        List<Future<Path>> listOfFuture = new ArrayList<>();
        for (Path path : files) {
            Future<Path> future = executor.submit(new MyCallable(path, text));
            listOfFuture.add(future);
        }
        for (Future<Path> f : listOfFuture) {
            try {
                Path myPath = f.get();

                if (myPath != null) {
                    findPath = myPath;
                    break;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdownNow();
        return findPath;
    }

    /**
     * Class for visiting files.
     */
    class MyVisitor extends SimpleFileVisitor<Path> {
        /**
         * Invoke when visit file.
         *
         * @param file  for visit.
         * @param attrs file attributes.
         * @return continue for visiting.
         * @throws IOException .
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files.add(file);
            return FileVisitResult.CONTINUE;
        }

        /**
         * Invoke when can't visit file.
         *
         * @param file for visit.
         * @param exc  exception.
         * @return continue for visiting
         * @throws IOException .
         */
        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

    /**
     * Class for searching text in file.
     */
    class MyCallable implements Callable<Path> {

        /**
         * File.
         */
        private Path path;

        /**
         * Text for searching.
         */
        private String text;

        /**
         * Constructor.
         *
         * @param path file for searching.
         * @param text for searching.
         */
        MyCallable(Path path, String text) {
            this.path = path;
            this.text = text;
        }

        /**
         * Searching text in file.
         *
         * @return path of file if file contains text or null.
         */
        @Override
        public Path call() {
            String textFile = null;
            try {
                textFile = new String(Files.readAllBytes(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (textFile != null && textFile.contains(text) && !fileIsFinding) {
                fileIsFinding = true;
                return path;
            }
            return null;
        }
    }

    /**
     * Main.
     *
     * @param args - path for passing directories and subdirectories.
     */
    public static void main(String[] args) {
        TextSearch search = new TextSearch();
        if (args.length != 1) {
            System.out.println("Enter the path of the"
                    + " directory into programm arguments to start the bypass or the path to the file for searching!");
        } else {
            Path startPath = Paths.get(args[0]);
            if (Files.isDirectory(startPath) || Files.isRegularFile(startPath)) {
                String text = null;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (text == null || text.isEmpty()) {
                        System.out.println("Enter the text to search for");
                        text = reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Searching file.........");
                Path path = search.searchPath(startPath, text);
                if (path != null) {
                    if (Files.isDirectory(startPath)) {
                        System.out.println("Congratulations! The text found in the file - " + path.toString());
                    } else {
                        System.out.println("Congratulations! The text in the specified file is "
                                + path.getFileName() + " found!");
                    }
                } else {
                    if (Files.isDirectory(startPath)) {
                        System.out.println("Unfortunately the text is missing from the files "
                                + "in the specified directory " + startPath.toString());
                    } else {
                        System.out.println("Unfortunately the text is missing in the file You specify "
                                + startPath.getFileName());
                    }
                }
            } else {
                System.out.println("The path you entered does not exist. "
                        + "Run the program again and specify the correct path to locate the file");
            }
        }
    }
}