package com.toolRental.pos.repositories;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.toolRental.pos.models.Tool;

public class ToolRepository {

    private static Map<String, Tool> toolStore;

    static {
        initializeStore();
    }

    private static void initializeStore() {
        toolStore = new HashMap<>();
        toolStore.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99"), true, false, false));
        toolStore.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolStore.put("LADW", new Tool("LADW", "Ladder", "Werner", new BigDecimal("1.99"), true, true, false));
        toolStore.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49"), true, false, true));
    }

    public static Tool getTool(String toolCode) {
        if (!toolStore.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code: " + toolCode);
        }
        return toolStore.get(toolCode);
    }

    public static boolean addTool(String toolCode, Tool tool) {
        if (toolStore.containsKey(toolCode)) {
            return false;
        }
        toolStore.put(toolCode, tool);
        return true;
    }

    public static boolean updateTool(String toolCode, Tool tool) {
        if (!toolStore.containsKey(toolCode)) {
            return false;
        }
        toolStore.put(toolCode, tool);
        return true;
    }

    public static boolean deleteTool(String toolCode) {
        if (!toolStore.containsKey(toolCode)) {
            return false;
        }
        toolStore.remove(toolCode);
        return true;
    }
}
