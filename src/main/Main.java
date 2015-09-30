package main;

import controller.PlayController;

/**
 *
 * @author Lexynux
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Iniciamos el controlador principal
        PlayController controller = new PlayController();
        
        // Se crean los jugadores
        controller.createPlayers("Computadora", "Usuario");
        
        // Se llena el mazo de cartas
        controller.randomizeCards();
        
        // Imprimimos el mazo de cartas
        System.out.println("--------- Generando cartas... -----------------");
        controller.printMainCardsStack();
        
        // Se reparten las cartas
        controller.dispenseCards();
        
        // Imprimimos el mazo de cartas
        System.out.println("\n------- Cartas restantes despúes de repartir: -----------------");
        controller.printMainCardsStack();
        
        // Inician las tiradas comenzando con el Juagador 1 (usuario)
        controller.startPlaying(1);
        
    }
    
}
