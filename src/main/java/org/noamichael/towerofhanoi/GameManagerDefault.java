package org.noamichael.towerofhanoi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Michael Kucinski
 */
@SessionScoped
public class GameManagerDefault implements GameManagerLocal, Serializable {

    private final static int NUMBER_OF_DISKS = 4;
    private int numberOfTurns;
    private boolean won;
    private List<Disk> columnOne;
    private List<Disk> columnTwo;
    private List<Disk> columnThree;

    public GameManagerDefault() {
        try {
            this.columnOne = new ArrayList();
            this.columnTwo = new ArrayList();
            this.columnThree = new ArrayList();

            this.columnOne.add(new Disk("disk4", 1, 4));
            this.columnOne.add(new Disk("disk3", 1, 3));
            this.columnOne.add(new Disk("disk2", 1, 2));
            this.columnOne.add(new Disk("disk1", 1, 1));
        } catch (Exception ex) {
            Logger.getLogger(GameManagerDefault.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void reset() {
        try {
            this.numberOfTurns = 0;
            this.won = false;
            this.columnOne = new ArrayList();
            this.columnTwo = new ArrayList();
            this.columnThree = new ArrayList();

            this.columnOne.add(new Disk("disk4", 1, 4));
            this.columnOne.add(new Disk("disk3", 1, 3));
            this.columnOne.add(new Disk("disk2", 1, 2));
            this.columnOne.add(new Disk("disk1", 1, 1));
        } catch (Exception ex) {
            Logger.getLogger(GameManagerDefault.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean onColumnOneDrop(Disk disk) {
        if (canDrop(disk, columnOne)) {
            incrementTurns(disk, 1);
            if (disk.getColumn() == 1) {
                return true;
            }
            updatePositions(columnOne);
            disk.setPosition(1);
            columnOne.add(disk);
            removeFromOldColumn(disk.getColumn(), disk);
            disk.setColumn(1);
            setWon(columnThree.size() == 4);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onColumnTwoDrop(Disk disk) {
        if (canDrop(disk, columnTwo)) {
            incrementTurns(disk, 2);
            if (disk.getColumn() == 2) {
                return true;
            }
            updatePositions(columnTwo);
            disk.setPosition(1);
            columnTwo.add(disk);
            removeFromOldColumn(disk.getColumn(), disk);
            disk.setColumn(2);
            setWon(columnThree.size() == 4);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onColumnThreeDrop(Disk disk) {
        if (canDrop(disk, columnThree)) {
            incrementTurns(disk, 3);
            if (disk.getColumn() == 3) {
                return true;
            }
            updatePositions(columnThree);
            disk.setPosition(1);
            columnThree.add(disk);
            removeFromOldColumn(disk.getColumn(), disk);
            disk.setColumn(3);
            setWon(columnThree.size() == 4);
            return true;
        } else {
            return false;
        }
    }

    private boolean canDrop(Disk disk, List<Disk> column) {
        if (column.isEmpty()) {
            return disk.getPosition() == 1;
        } else {
            if (disk.equals(column.get(column.size() - 1))) {
                return true;
            }
            return column.get(column.size() - 1).getWeight() > disk.getWeight() && disk.getPosition() <= 1;
        }
    }

    public void incrementTurns(Disk disk, int column) {
        if (disk.getColumn() != column) {
            this.numberOfTurns++;
        }
    }

    private void updatePositions(List<Disk> column) {
        for (Disk disk : column) {
            disk.setPosition(disk.getPosition() + 1);
        }
    }

    private void removeFromOldColumn(int column, Disk disk) {
        List<Disk> currentColumn = null;
        switch (column) {
            case 1: {
                currentColumn = columnOne;
                columnOne.remove(disk);
                break;
            }
            case 2: {
                currentColumn = columnTwo;
                columnTwo.remove(disk);
                break;
            }
            case 3: {
                currentColumn = columnThree;
                columnThree.remove(disk);
                break;
            }

        }
        for (Disk oldDisk : currentColumn) {
            oldDisk.setPosition(oldDisk.getPosition() - 1);
        }
    }

    /**
     * @return the columnOne
     */
    @Override
    public List<Disk> getColumnOne() {
        List<Disk> temp = new ArrayList();
        copy(columnOne, temp);
        Collections.reverse(temp);
        return temp;
    }

    /**
     * @return the columnTwo
     */
    @Override
    public List<Disk> getColumnTwo() {
        List<Disk> temp = new ArrayList();
        copy(columnTwo, temp);
        Collections.reverse(temp);
        return temp;
    }

    /**
     * @return the columnThree
     */
    @Override
    public List<Disk> getColumnThree() {
        List<Disk> temp = new ArrayList();
        copy(columnThree, temp);
        Collections.reverse(temp);
        return temp;
    }

    private void copy(List<Disk> source, List<Disk> dest) {
        for (Disk d : source) {
            dest.add(d);
        }
    }

    /**
     * @return the won
     */
    @Override
    public boolean isWon() {
        return won;
    }

    /**
     * @param won the won to set
     */
    private void setWon(boolean won) {
        this.won = won;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfTurns() {
        return this.numberOfTurns;
    }

    @Override
    public int minimumNumberOfTurns() {
        return ((int) Math.pow(2, NUMBER_OF_DISKS)) - 1;
    }
}
