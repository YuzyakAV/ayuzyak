package ru.job4j.threads;

/**
 * WordsAndSpaces.
 *
 * @author Ayuzyak
 * @since 10.08.2017
 * @version 1.0
 */
public class WordsAndSpaces {
    /**
     * Number of words.
     */
    private int nWords = 0;

    /**
     * Number of spaces.
     */
    private int nSpaces = 0;

    /**
     * Variable for stopping threads.
     */
    private volatile boolean programStop = false;

    /**
     * Count words and spaces in text by two threads.
     * @param s - text.
     */
    public void numberOfWordsAndSpaces(String s) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                char[] array = s.toCharArray();
                boolean isWord = false;
                for (int i = 0; i < array.length && !Thread.currentThread().isInterrupted(); i++) {
                    if (System.currentTimeMillis() - startTime > 1000 || programStop) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    if (Character.isLetter(array[i])) {
                        isWord = true;
                    }
                    if (!Character.isLetter(array[i]) && isWord) {
                        nWords++;
                        isWord = false;
                        System.out.println("Counted: " + nWords + " words");
                    }
                    if (i == array.length - 1 && isWord) {
                        nWords++;
                        System.out.println("Counted: " + nWords + " words");
                    }
                }
                System.out.println("Number of words: " + nWords);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                char[] array = s.toCharArray();
                for (int i = 0; i < array.length && !Thread.currentThread().isInterrupted(); i++) {
                    if (System.currentTimeMillis() - startTime > 1000 || programStop) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    if (array[i] == 32) {
                        nSpaces++;
                        System.out.println("Counted: " + nSpaces + " spaces");
                    }
                }
                System.out.println("Number of spaces: " + nSpaces);
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for program stopping counting words and spaces.
     */
    public void stopCounting() {
        this.programStop = true;
    }

    /**
     * Main method.
     * @param args for cmd.
     */
    public static void main(String[] args) {
        System.out.println("Program is starting.......");
        WordsAndSpaces wordsAndSpaces = new WordsAndSpaces();
        wordsAndSpaces.numberOfWordsAndSpaces("Number of words should be twelve. "
                + "Number of spaces should be eleven");
        System.out.println("...............End");
    }
}