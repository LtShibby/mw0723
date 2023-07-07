package com.cardinal.pos.repositories;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.cardinal.pos.models.Tool;

public class ToolRepository {

    private static Map<String, Tool> toolStore;

    public ToolRepository() {
        toolStore = new HashMap<>();
        initializeStore();
    }

    private void initializeStore() {
        toolStore.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal(2.99), true, false, false));
        toolStore.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal(2.99), true, false, false));
        toolStore.put("LADW", new Tool("LADW", "Ladder", "Werner", new BigDecimal(1.99), true, true, false));
        toolStore.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal(1.49), true, false, true));
    }

    public Tool getTool(String toolCode) {
        if (!toolStore.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code: " + toolCode);
        }
        return toolStore.get(toolCode);
    }
}
