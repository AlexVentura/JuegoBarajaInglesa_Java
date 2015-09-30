package controller;

import java.util.Stack;
import models.Player;
import utilities.Node;

/**
 *
 * @author Lexynux
 */
public class PlayController {

    // Array de jugadores
    private Player[] players;
    // Rango de (M - N) para generar los valores de las cartas
    private final int M = 1, N = 10;
    // Pila principal de cartas
    private Stack mainCardsStack;
    // Bandeara de paro del juego
    private boolean stopGame = false;

    public PlayController() {
        mainCardsStack = new Stack();
    }

    /**
     * Genera numeros aleatorios para las cartas
     */
    public void randomizeCards() {
        for (int k = 0; k < 30; k++) {
            // Valor entre M y N, ambos incluidos.
            int integerValue = (int) Math.floor(Math.random() * (N - M + 1) + M);
            mainCardsStack.push(integerValue);
        }
    }

    /**
     * Creamos tantos jugadores como nombres nos pasen en playersNames
     *
     * @param playersNames Array con los nombres de juagadores
     */
    public void createPlayers(String... playersNames) {
        players = new Player[playersNames.length];

        for (int k = 0; k < playersNames.length; k++) {
            players[k] = new Player(playersNames[k]);
        }
    }

    /**
     * Reparte las cartas a los x jugadores
     */
    public void dispenseCards() {
        for (Player player : players) {
            for (int k = 0; k < 3; k++) {
                player.getCardsList().addLast(mainCardsStack.pop());
            }

            System.out.print("Cartas de " + player.getName() + ": ");
            player.getCardsList().printElements();
            System.out.println("");
        }
    }

    /**
     * Imprime las caras en la pila princpal
     */
    public void printMainCardsStack() {
        Object[] data = mainCardsStack.toArray();
        System.out.print("\nPila Principal = [");
        for (Object data1 : data) {
            System.out.print(data1 + ",");
        }
        System.out.print("]\n");
    }

    private void printPlayerCards(Player... players) {
        for (Player player : players) {
            System.out.print("Cartas de " + player.getName() + ": ");
            player.getCardsList().printElements();
            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Metodo que inicia todo el juego
     *
     * @param whoStarts indica el jugador que inicia tirando
     */
    public void startPlaying(int whoStarts) {
        // Jugador que tiene el turno
        int nowPlaying = whoStarts;

        // Carta con la que inicia el juego
        int actualCard = (int) mainCardsStack.pop();

        do {
            System.out.println("\n================= Jugador que tira: " + nowPlaying + " - " + players[nowPlaying].getName() + " =================");
            System.out.println("\tCarta en juego: [ " + actualCard + " ]\n");

            // Indica si se encontro una carta igual a la que esta en juego
            boolean cardFound = lookForTheActualCard(players[nowPlaying], actualCard);

            while (!cardFound && !stopGame) {
                System.out.print("\tEl jugador no tiene esa carta, toma una!");
                getAnotherCard(players[nowPlaying]);
                cardFound = lookForTheActualCard(players[nowPlaying], actualCard);
            }

            if (cardFound) {
                System.out.println("\n\t=> Yeii, el jugador tiene la carta!!!");
                System.out.println("\n=================================================================");
                // Tira nueva carta el jugador en turno para continuar el juego
                actualCard = setNewActualCard(players[nowPlaying]);
            }

            // Imprimir los tableros
            System.out.println("\n------- Tableros: -------");
            printMainCardsStack();
            printPlayerCards(players);

            // Validar de quien es el turno de tirar
            nowPlaying = whoGoesNow(nowPlaying);

            // Validamos si el juego termina o continua
            stopGame = isThereWinner();

        } while (stopGame == false);
    }

    private boolean lookForTheActualCard(Player player, int actualCard) {
        // Posicion de la carta encontrada en la lista de cartas del jugador
        int position = 1;
        // Resultado de la busqueda
        boolean found = false;

        // Buscamos si el jugador tiene la carta correspondiente
        for (Node aux = player.getCardsList().getHead(); aux != null; aux = aux.getNextElement(), position++) {
            // Si la encuentra, borramos la carta de la lista del jugador
            if (aux.getObject().toString().equals(Integer.toString(actualCard))) {
                player.getCardsList().deleteByPosition(position);
                found = true;
                aux.setNextElement(null);
            }
        }
        return found;
    }

    private void getAnotherCard(Player player) {
        /* Agregamos una nueva carta a la lista de cartas del jugador
         tomada de la pila de cartas principal, si es que hay
         */
        if (!mainCardsStack.empty()) {
            System.out.println("\tCarta obtenida: {" + mainCardsStack.peek() + "}");
            player.getCardsList().addLast(mainCardsStack.pop());
        } else {
            System.out.println("\n\n\n++++++++++++++ Ya no hay cartas! ++++++++++++++\n\n");
            stopGame = true;
        }
    }

    private int setNewActualCard(Player player) {
        int cardValue = -1;
        
        if (!player.getCardsList().isEmpty()) {
            // Obtenemos la carta en la última posición de su lista(tablero) del jugador
            cardValue = (int) player.getCardsList().getTail().getObject();
            // Borramos la carta de su lista(tablero)
            player.getCardsList().deleteLast();
        }

        return cardValue;
    }

    private int whoGoesNow(int nowPlaying) {
        /* Si esta jugando la Computadora(0), ahora sera turno del Usuario(1)
         sino, entonces es turno de la computadora
         */

        return (nowPlaying == 0) ? 1 : 0;
    }

    private boolean isThereWinner() {
        // Resultado del juego
        boolean result = false;

        // Validamos si hay cartas en la pila principal o no
        if (mainCardsStack.empty()) {
            // Como ya NO hay cartas, verificamos que jugador tiene menos cartas
            if (players[0].getCardsList().getLength() < players[1].getCardsList().getLength()) {
                System.out.println("\n\n***** Gano la computadora! *****\n\n");
            } else if (players[1].getCardsList().getLength() < players[0].getCardsList().getLength()) {
                System.out.println("\n\n***** Gano el Usuario! *****\n\n");
            } else {
                System.out.println("\n\n----- Hubo un empate -----\n\n");
            }
            result = true;
        } else {
            // Si aún HAY cartas, verificamos si un jugador tiene 0 cartas
            if (players[0].getCardsList().isEmpty()) {
                System.out.println("\n\n***** Gano la computadora! *****\n\n");
                result = true;
            } else if (players[1].getCardsList().isEmpty()) {
                System.out.println("\n\n***** Gano el Usuario! *****\n\n");
                result = true;
            }
        }

        return result;
    }

}
