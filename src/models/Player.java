package models;

import utilities.LmxList;

/**
 *
 * @author Lexynux
 * @description Clase que representa un jugador con nombre y sus cartas disponibles
 */
public class Player {

    // Nombre del Jugador
    private String name;
    // Lista de Cartas del jugador
    private LmxList<Integer> cardsList;

    public Player(String name) {
        // Pedimos nombr del Jugador
        this.name = name;
        // Se inicializa la lista de cartas
        this.cardsList = new LmxList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LmxList getCardsList() {
        return cardsList;
    }

    public void setCardsList(LmxList cardsList) {
        this.cardsList = cardsList;
    }

}
