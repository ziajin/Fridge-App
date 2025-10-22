package persistence;

import org.json.JSONObject;

public interface Writable {
    //EFFECTS: return as JSON object
    JSONObject toJson();
}
