package Robotron;


import java.awt.*;
import java.util.LinkedList;

/**
 * Handler class which stores all the GameObjects in a LinkedList and has method to operate on them
 */
public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    /**
     * Method that updates the data of all GameObject stored within the Handlers LinkedList
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }

    }

    /**
     * Method that renders all the GameObjects stored within the Handlers LinkedList
     *
     * @param g Instance of Graphics upon which all render methods are called
     */
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }

    }

    /**
     * Method which add a GameObject to the LinkedList
     *
     * @param object GameObject which is getting added
     */
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    /**
     * Remove a given Object from the LinkedList
     *
     * @param object GameObject which is getting removed
     */
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    /**
     * Delets all GameObjects from the LinkedList simple by instantiating it to a new instance of LinkedList
     */
    public void clearHandler() {
        this.object = new LinkedList<GameObject>();
    }
}

