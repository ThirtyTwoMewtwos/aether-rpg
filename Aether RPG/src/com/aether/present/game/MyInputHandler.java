package com.aether.present.game;
import com.jme.input.InputHandler;
import com.jme.input.InputSystem;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.action.KeyNodeBackwardAction;
import com.jme.input.action.KeyNodeForwardAction;
import com.jme.input.action.KeyNodeRotateLeftAction;
import com.jme.input.action.KeyNodeRotateRightAction;
import com.jme.scene.Spatial;
 
/**
 * Input Handler for the Flag Rush game. This controls a supplied spatial
 * allowing us to move it forward, backward and rotate it left and right.
 * @author Mark Powell
 *
 */
public class MyInputHandler extends InputHandler {
 
    /**
     * Supply the node to control and the api that will handle input creation.
     * @param node the node we wish to move
     * @param api the library that will handle creation of the input.
     */
    public MyInputHandler(Spatial node) {
        setKeyBindings();
        setActions(node);
    }
 
    /**
     * creates the keyboard object, allowing us to obtain the values of a keyboard as keys are
     * pressed. It then sets the actions to be triggered based on if certain keys are pressed (WSAD).
     * @param api
     */
    private void setKeyBindings() {
        KeyBindingManager keyboard = KeyBindingManager.getKeyBindingManager();
 
        keyboard.set("forward", KeyInput.KEY_W);
        keyboard.set("backward", KeyInput.KEY_S);
        keyboard.set("turnRight", KeyInput.KEY_D);
        keyboard.set("turnLeft", KeyInput.KEY_A);
    }
 
    /**
     * assigns action classes to triggers. These actions handle moving the node forward, backward and 
     * rotating it.
     * @param node the node to control.
     */
    private void setActions(Spatial node) {
        KeyNodeForwardAction forward = new KeyNodeForwardAction(node, 15f);
        addAction(forward, "forward", true);
 
        KeyNodeBackwardAction backward = new KeyNodeBackwardAction(node, 5f);
        addAction(backward, "backward", true);
 
        KeyNodeRotateRightAction rotateRight = new KeyNodeRotateRightAction(node, 2f);
        rotateRight.setLockAxis(node.getLocalRotation().getRotationColumn(1));
        addAction(rotateRight, "turnRight", true);
 
        KeyNodeRotateLeftAction rotateLeft = new KeyNodeRotateLeftAction(node, 2f);
        rotateLeft.setLockAxis(node.getLocalRotation().getRotationColumn(1));
        addAction(rotateLeft, "turnLeft", true);
    }
}
