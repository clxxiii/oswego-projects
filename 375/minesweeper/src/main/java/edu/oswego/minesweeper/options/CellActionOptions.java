package edu.oswego.minesweeper.options;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * A data class that passes xy-coordinates, as well as the action
 * to take on the requested square.
 */
public class CellActionOptions {
    public CellAction action;
    public int x;
    public int y;


    public CellActionOptions(JSONObject data) throws ParseException {
        // To build this object, all arguments are required.
        // If one is missing and one of these throw, shame on the user.
        this.x = data.getInt("x");
        this.y = data.getInt("y");
        String action = data.getString("action");

        if (action.equalsIgnoreCase("flag")) {
            this.action = CellAction.FLAG;
        }
        else if (action.equalsIgnoreCase("reveal")) {
            this.action = CellAction.REVEAL;
        }
        else {
            throw new ParseException("The given action is invalid", 2);
        }

    }
}
