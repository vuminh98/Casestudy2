package IOTextFile;

import Account.User;

import java.io.*;
import java.util.ArrayList;

public class IOTextFile<E> {
    public void writeFile( ArrayList<E> arrayList, String pathFile) {

        try (ObjectOutputStream objectOutputStream
                     = new ObjectOutputStream(new FileOutputStream(pathFile))) {
            objectOutputStream.writeObject(arrayList);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<E> readFile(String pathFile) {
        ArrayList<E> arrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathFile);
            if (fileInputStream.available() > 0) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                arrayList = (ArrayList<E>) objectInputStream.readObject();
            }
            return arrayList;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return arrayList;
    }
}

