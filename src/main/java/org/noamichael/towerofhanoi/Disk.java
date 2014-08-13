package org.noamichael.towerofhanoi;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Michael Kucinski
 */
public class Disk implements Serializable {

    private String image;
    private int column = 0;
    private int weight;
    private int position;

    public Disk(String image, int column, int weight) throws Exception {
//        if (column < 0 || column > 3) {
//            throw new Exception("Column must range from [1,3] inclusive!");
//        }
//        if (weight < 0 || weight > 4) {
//            throw new Exception("Disk weight must range from [1,4] inclusive!");
//        }
        this.image = image;
        this.column = column;
        this.weight = weight;
        this.position = weight;
    }

    public Disk() {
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.image);
        hash = 41 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Disk other = (Disk) obj;
        return this.weight == other.weight;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }
}
