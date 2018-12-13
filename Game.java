
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game 
{
    private Parser parser;
    private Stack roomHistory = new Stack();
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        createPlayers();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room hall, salon, cocina, despensa, estudio, desvan;
      
        // create the rooms
        hall = new Room("hall", "en la entrada principal de la mansion");
        salon = new Room("salon", "en el salon principal");
        cocina = new Room("cocina", "en la cocina");
        despensa = new Room("despensa", "en la despensa de la cocina");
        estudio = new Room("estudio", "en el estudio");
        desvan = new Room("desvan", "en el desvan");
        
        // initialise room exits
        hall.setExit("east", estudio);
        hall.setExit("west", salon);
        // set items
        hall.setItem(new Item("libro", "Un libro viejo y lleno de polvo, algunas páginas han sido arrancadas.", 430));
        hall.setItem(new Item("Linterna", "Una linterna de mano, utiliza 2 pilas AA", 384));

        estudio.setExit("north", hall);
        estudio.setExit("south", desvan);

        desvan.setExit("north", estudio);
        
        salon.setExit("north", hall);
        salon.setExit("south", cocina);

        cocina.setExit("north", salon);
        cocina.setExit("west", despensa);
        despensa.setExit("east", cocina);
        
        player.setCurrentRoom(hall);
        player.addItem("cuchillo", new Item("cuchillo", "Un viejo cuchillo", 45));
        
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        
        System.out.println("Gracias por jugar. Adios.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido al Mundo de Zuul!");
        System.out.println("Mundo de Zuul es un aburrido juego de aventuras.");
        System.out.println("Escribe 'ayuda' si necesitas ayuda.");
        System.out.println();
        printUbicationInfo();
    }
    
    private void printUbicationInfo()
    {
        System.out.println(player.getCurrentRoom().getFullDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        
        CommandWord commandWord =  command.getCommandWord();
        
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("No entiendo lo que tratas de decir...");
            return false;
        }


        if (commandWord == CommandWord.AYUDA) {
            printHelp();
        }
        else if (commandWord == CommandWord.IR) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.VER) {
            ver();
        }
        else if (commandWord == CommandWord.TOMAR) {
            tomar(command);
        }
        else if (commandWord == CommandWord.DEJAR) {
            dejar(command);
        }
        else if (commandWord == CommandWord.COMER) {
            comer();
        }
        else if (commandWord == CommandWord.VOLVER) {
            volver();
        }
        else if (commandWord == CommandWord.ELEMENTOS) {
            mostrarEquipo();
        }
        else if (commandWord == CommandWord.SALIR) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;

    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estas solo. Estas perdido");
        System.out.println("en una mansion.");
        System.out.println();
        System.out.println("Los comandos disponibles son: ");
      
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("No hay puerta!");
        }
        else {
            roomHistory.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            
            printUbicationInfo();
        }
    }
    
    private void ver()
    {
        System.out.println(player.getCurrentRoom().getFullDescription());
    }
    
    private void comer()
    {
        System.out.println("Ya has comido, no tienes mas hambre");
    }
    
    private void volver()
    {
        if (! roomHistory.empty()) {
            player.setCurrentRoom((Room) roomHistory.pop());
            printUbicationInfo();
        } else {
            System.out.println("Has llegado al principio...");
        }
    }
    
    private void tomar(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Tomar el qué?");
            return;
        }

        String itemName = command.getSecondWord();
        Room r = player.getCurrentRoom();

        Item item = r.getItem(itemName);
        if(item != null) {
            player.addItem(itemName, item);
            System.out.println("Has recogido" + itemName + ".");
        } else {
            System.out.println("No hay ningún objeto " + itemName + ".");
        }
    }
    
    private void dejar(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir adónde?");
            return;
        }

        String itemName = command.getSecondWord();
        
        Item item = player.getItem(itemName);
        if(item != null) {
            player.getCurrentRoom().setItem(item);
        } else {
            System.out.println("No tienes el objeto " + itemName + ".");
        }
        
    }
    
    private void mostrarEquipo() {
        System.out.println(player.getItems());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * TODO: eliminar o refactorizar
     */
    private void createPlayers() {
        //player.setCurrentRoom(hall);  // start game outside
    }
}
