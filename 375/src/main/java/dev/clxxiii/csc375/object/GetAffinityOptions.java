package dev.clxxiii.csc375.object;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAffinityOptions {


    final int[] indexes = new int[2];
    public GetAffinityOptions(JSONObject json) {
       JSONArray array = json.getJSONArray("slots");
       indexes[0] = array.getInt(0);
       indexes[1] = array.getInt(1);
    }

    public int getFirst() {
        return indexes[0];
    }

    public int getSecond() {
        return indexes[1];
    }
}
