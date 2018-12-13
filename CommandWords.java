
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class CommandWords
{
    // a constant array that holds all valid command words
//    private static final String[] validCommands = {
//        "ir", "ver", "comer", "tomar", "dejar",  "volver", "elementos", "salir", "ayuda"
//    };
    
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        validCommands.put("ir", CommandWord.IR);
        validCommands.put("ver", CommandWord.VER);
        validCommands.put("comer", CommandWord.COMER);
        validCommands.put("tomar", CommandWord.TOMAR);
        validCommands.put("dejar", CommandWord.DEJAR);
        validCommands.put("volver", CommandWord.VOLVER);
        validCommands.put("elementos", CommandWord.ELEMENTOS);
        validCommands.put("salir", CommandWord.SALIR);
        
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        /*for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;*/
        return validCommands.containsKey(aString);
    }
    
    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    
    /**
     * Print all valid commands
     */
    public String getCommandList()
    {
        String commandList = "";
     
        for (String command : validCommands.keySet())
            commandList += command + " ";
        
        return commandList;
    }
}
