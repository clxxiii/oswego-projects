package dev.clxxiii.csc375.object;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAffinityOptions {


    public final int x1;
    public final int y1;
    public final int x2;
    public final int y2;
    public GetAffinityOptions(JSONObject json) {
        x1 = json.getInt("x1");
        y1 = json.getInt("y1");
        x2 = json.getInt("x2");
        y2 = json.getInt("y2");
    }

}
