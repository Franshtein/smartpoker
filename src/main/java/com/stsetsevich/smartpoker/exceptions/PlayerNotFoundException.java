package com.stsetsevich.smartpoker.exceptions;

import org.apache.logging.log4j.message.Message;

public class PlayerNotFoundException extends Exception{
    public int playerPosition;
    private String message;
    public PlayerNotFoundException(int playerPosition, String playerName) {
        message="Player "+playerName+" not found. Set Empty Seat";
        this.playerPosition=playerPosition;
    }
    public String toString()
    {
        return message;
    }
}
