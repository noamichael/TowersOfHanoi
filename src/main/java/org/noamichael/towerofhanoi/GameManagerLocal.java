package org.noamichael.towerofhanoi;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * A class which manages a single round of tower of hanoi.
 *
 * @author Michael Kucinski
 */
@Local
public interface GameManagerLocal extends Serializable {

    /**
     * Resets the game to a new round.
     */
    public void reset();

    /**
     * The method to run upon dropping a disk on the first column.
     *
     * @param disk the disk that was dropped
     * @return true if the move was legal.
     */
    public boolean onColumnOneDrop(Disk disk);

    /**
     * The method to run upon dropping a disk on the second column.
     *
     * @param disk the disk that was dropped
     * @return true if the move was legal.
     */
    public boolean onColumnTwoDrop(Disk disk);

    /**
     * The method to run upon dropping a disk on the third column.
     *
     * @param disk the disk that was dropped
     * @return true if the move was legal.
     */
    public boolean onColumnThreeDrop(Disk disk);

    /**
     * @return the columnOne
     */
    public List<Disk> getColumnOne();

    /**
     * @return the columnTwo
     */
    public List<Disk> getColumnTwo();

    /**
     * @return the columnThree
     */
    public List<Disk> getColumnThree();

    /**
     * @return true if the game was won.
     */
    public boolean isWon();

    /**
     * 
     * @return the number of valid turns the player has 
     */
    public int getNumberOfTurns();
    
    /**
     * 
     * @return the minimum number of turns it can possibly take to win the 
     * current game.
     */
    public int minimumNumberOfTurns();

}
