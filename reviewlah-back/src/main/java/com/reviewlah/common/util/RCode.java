package com.reviewlah.common.util;

import java.util.HashMap;
import java.util.Map;

public class RCode extends HashMap<String, Object> {
    public RCode() {
        this.put((String)"code", 200);
        this.put((String)"msg", "Success");
    }

    public RCode put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static RCode ok() {
        return new RCode();
    }

    public static RCode ok(String msg) {
        RCode r = new RCode();
        r.put((String)"msg", msg);
        return r;
    }

    public static RCode ok(Map<String, Object> map) {
        RCode r = new RCode();
        r.putAll(map);
        return r;
    }

    public static RCode error(int code, String msg) {
        RCode r = new RCode();
        r.put((String)"code", code);
        r.put((String)"msg", msg);
        return r;
    }

    public static RCode error(String msg) {
        return error(500, msg);
    }

    public static RCode error() {
        return error(500, "Unknown Exception");
    }
}
