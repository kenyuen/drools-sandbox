package org.yuenio.droolSandbox;

import java.util.List;
import java.util.Map;

public class DrlHelperOutput {
    private List<Object> objects;
    private Map<String, Object> facts;

    public DrlHelperOutput(List<Object> objects, Map<String, Object> facts) {
        this.objects = objects;
        this.facts = facts;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public Map<String, Object> getFacts() {
        return facts;
    }
}
