package atlg4.ultimate.g47923.model;

import atlg4.ultimate.g47923.dto.UserDTO;

/**
 * Represents a person that has played the game once.
 *
 * @author Logan Farci (47923)
 */
class User {

    private final String pseudonym;
    private int nbOfVictories;
    private int nbOfExaequos;
    private int nbOfDefeats;

    User(String pseudonym, int nbOfVictories, int nbOfExaequos, int nbOfDefeats) {
        this.pseudonym = pseudonym;
        this.nbOfVictories = nbOfVictories;
        this.nbOfExaequos = nbOfExaequos;
        this.nbOfDefeats = nbOfDefeats;
    }

    User(UserDTO user) {
        this(
                user.getPseudonym(),
                user.getNbOfVictories(),
                user.getNbOfTies(),
                user.getNbOfDefeats()
        );
    }

    String getPseudonym() {
        return pseudonym;
    }

    int getNbOfVictories() {
        return nbOfVictories;
    }

    int getNbOfExaequos() {
        return nbOfExaequos;
    }

    int getNbOfDefeats() {
        return nbOfDefeats;
    }

    void addAVictory() {
        nbOfVictories++;
    }

    void addATie() {
        nbOfExaequos++;
    }

    void addADefeat() {
        nbOfDefeats++;
    }

    /**
     * Converts this user to a data transfer object.
     */
    UserDTO toDTO() {
        return new UserDTO(pseudonym, nbOfVictories, nbOfExaequos, nbOfDefeats);
    }

}
