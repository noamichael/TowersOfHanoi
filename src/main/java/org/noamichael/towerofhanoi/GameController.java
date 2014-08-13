package org.noamichael.towerofhanoi;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author Michael Kucinski
 */
@Named
@SessionScoped
public class GameController implements Serializable {

    @EJB
    private GameManagerLocal gameManager;
    private Disk currentDisk;
    private transient List<Disk> columnOne;
    private transient List<Disk> columnTwo;
    private transient List<Disk> columnThree;

    public GameController() {
        Disk disk = new Disk();
        disk.setImage(String.valueOf(0));
        this.currentDisk = disk;
    }

    public int getNumberOfTurns() {
        return gameManager.getNumberOfTurns();
    }

    public int getMinimumTurns() {
        return gameManager.minimumNumberOfTurns();
    }

    public void reset() throws Exception {
        System.out.println("Resetting...");
        gameManager.reset();
        update();
    }

    public void onColumnOneDrop(DragDropEvent event) {
        boolean result = gameManager.onColumnOneDrop(currentDisk);
        checkGameStatus(result);
        this.currentDisk = null;
        update();
    }

    public void onColumnTwoDrop(DragDropEvent event) {
        boolean result = gameManager.onColumnTwoDrop(currentDisk);
        checkGameStatus(result);
        this.currentDisk = null;
        update();
    }

    public void onColumnThreeDrop(DragDropEvent event) {

        boolean result = gameManager.onColumnThreeDrop(currentDisk);
        checkGameStatus(result);
        this.currentDisk = null;
        update();
    }

    public void checkGameStatus(boolean result) {
        if (result) {
            if (gameManager.isWon()) {
                if (this.getNumberOfTurns() <= gameManager.minimumNumberOfTurns()) {
                    addMsg("Congratulations! You won with the minimum amount of moves!");
                } else {
                    addMsg("Congratulations! You won, but not in the minimum amount of moves. Try again!");
                }
            }
        } else {
            addMsg("Illegal move!");
        }
    }

    public void addMsg(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    public List<Disk> getColumnOne() {
        if (columnOne == null) {
            columnOne = gameManager.getColumnOne();
        }
        return columnOne;
    }

    public List<Disk> getColumnTwo() {
        if (columnTwo == null) {
            columnTwo = gameManager.getColumnTwo();
        }
        return columnTwo;
    }

    public List<Disk> getColumnThree() {
        if (columnThree == null) {
            columnThree = gameManager.getColumnThree();
        }
        return columnThree;
    }

    public void onColumnOneClick(Disk disk) {
        this.currentDisk = disk;
    }

    public void onColumnTwoClick(Disk disk) {
        this.currentDisk = disk;
    }

    public void onColumnThreeClick(Disk disk) {
        this.currentDisk = disk;
    }

    private void update() {
        this.columnOne = gameManager.getColumnOne();
        this.columnTwo = gameManager.getColumnTwo();
        this.columnThree = gameManager.getColumnThree();
    }

    /**
     * @return the currentDisk
     */
    public Disk getCurrentDisk() {
        return currentDisk;
    }

    /**
     * @param currentDisk the currentDisk to set
     */
    public void setCurrentDisk(Disk currentDisk) {
        if (currentDisk == null) {
            return;
        }
        System.out.println(currentDisk.getImage());
        this.currentDisk = currentDisk;
    }

    public boolean isWon() {
        return this.gameManager.isWon();
    }

}
