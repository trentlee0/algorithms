package designpattern.decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DecoratorTest {
    public static void main(String[] args) throws FileNotFoundException {
        new DataInputStream(new FileInputStream("C:"));
    }
}
