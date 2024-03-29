package atlg4.g47923.anagram.players;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The <code> User </code> represents a connected user.
 */
public class User implements Serializable {

    /**
     * Represents the server administrator.
     */
    public static final User ADMIN = new User(0, "ADMIN");

    /**
     * Represents a virtual user useful for broadcast.
     */
    public static final User EVERYBODY = new User(0, "EVERYBODY");

    private final int id;
    private String name;
    private int nbSolvedWord;
    private InetAddress address;

    /**
     * Constructs a connected user.
     *
     * @param id userID of the connected user.
     * @param name name of the connected user.
     * @param address IP address of the connected user.
     */
    public User(int id, String name, InetAddress address) {
        this.id = id;
        this.name = name;
        this.nbSolvedWord = 0;
        this.address = address;
    }

    /**
     * Constructs a connected user.
     *
     * @param id userID of the connected user.
     * @param name name of the connected user.
     */
    public User(int id, String name) {
        this(id, name, null);
    }

    /**
     * Return the user name.
     *
     * @return the user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the number of words this user has solved.
     *
     * @return the number of words this user has solved.
     */
    public int getNbSolvedWords() {
        return nbSolvedWord;
    }

    /**
     * Sets the number of words this user has solved.
     *
     * @param value is the number of words this user has solved.
     */
    public void setNbSolvedWords(int value) {
        nbSolvedWord = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }

    boolean is(int id) {
        return this.id == id;
    }

    /**
     * Return the userID.
     *
     * @return the userID.
     */
    public int getId() {
        return id;
    }

    void setName(String name) {
        this.name = name;
    }

    /**
     * Return the user IP address .
     *
     * @return the user IP address .
     */
    public String getAddress() {
        return address.getHostAddress();
    }

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }

}
