package persistence;

import org.json.JSONObject;

// Based on the persistence package in the Workroom repo provided in phase 2

public interface Writable {
    //EFFECTS: return as JSON object
    JSONObject toJson();
}
