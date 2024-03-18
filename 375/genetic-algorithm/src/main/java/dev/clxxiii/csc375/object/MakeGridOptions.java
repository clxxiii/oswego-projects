package dev.clxxiii.csc375.object;

import org.json.JSONObject;

public class MakeGridOptions {
    public int size = 7;
    public int types = 4;

    public MakeGridOptions(JSONObject data) {
        if (data.get("size") != null) {
            this.size = data.getInt("size");
        }

        if (data.get("types") != null) {
            this.types = data.getInt("types");
        }
    }
}
