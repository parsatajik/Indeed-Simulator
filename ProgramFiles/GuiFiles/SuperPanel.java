package ProgramFiles.GuiFiles;

import javax.swing.*;

import ProgramFiles.User;
import ProgramFiles.Storage;

public class SuperPanel extends JPanel {

    private static User currentUser;
    private static Storage storage;

    static User getCurrentUser() { return currentUser; }

    static Storage getStorage() { return storage; }

    static void setCurrentUser(User newCurrentUser) { currentUser = newCurrentUser; }

    static void setStorage(Storage newStorage) { storage = newStorage; }

}
