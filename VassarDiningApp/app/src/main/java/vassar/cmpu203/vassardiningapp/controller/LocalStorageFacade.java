package vassar.cmpu203.vassardiningapp.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import vassar.cmpu203.vassardiningapp.model.User;

public class LocalStorageFacade {

    private final File directory;
    private static final String FILENAME = "VassarDiningUser";

    public LocalStorageFacade(@NonNull File directory) {
        this.directory = directory;
    }

    public void saveUser(@NonNull User user) {
        File outFile = new File(directory, FILENAME);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            String emsg = String.format("Couldn't find file: %s", outFile.getAbsolutePath());
            Log.e("VassarDiningApp", emsg);
            e.printStackTrace();
        } catch (IOException e) {
            final String emsg = String.format("I/O error writing to %s", outFile);
            Log.e("VassarDiningApp", emsg);
            e.printStackTrace();
        }
    }

    @Nullable
    public User retrieveLedger() {
        User user = null; // null to begin with for negative outcome

        File inFile  = new File(this.directory, FILENAME);
        if (inFile.isFile()) { // must check that the file actually exists
            try {
                FileInputStream fileInStream = new FileInputStream(inFile); // get a stream to read from
                ObjectInputStream objectInStream = new ObjectInputStream(fileInStream); // lets us read objects instead of just numbers
                user = (User) objectInStream.readObject(); // must downcast from Object
            } catch (IOException e) {
                final String emsg = String.format("I/O error reading from %s", inFile);
                Log.e("VassarDiningApp", emsg);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                final String emsg = String.format("Can't find class of object from %s", inFile);
                Log.e("VassarDiningApp", emsg);
                e.printStackTrace();
            }
        }
        return user;
    }
}
