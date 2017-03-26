package ru.job4j;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * Class for delete abuse words from stream.
 * @author Ayuzyak
 * @since 22.03.2017
 * @version 1.0
 */
public class DropAbuse {
    /**
     * Read text with abuse words from stream and wright text without them to outputStream.
     * @param in - InputStream.
     * @param out - Outputstream.
     * @param abuse words.
     */
    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            String string = null;
            while (reader.ready()) {
                string = reader.readLine();
                for (String str : abuse) {
                    if (string.contains(str)) {
                        string = string.replaceAll(str, "");
                    }
                }
                writer.write(string);
                if (reader.ready()) {
                    writer.write(System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}