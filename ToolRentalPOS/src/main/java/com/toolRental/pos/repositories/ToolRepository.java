package com.toolRental.pos.repositories;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.toolRental.pos.models.Tool;

public class ToolRepository {
    private static final Map<String, Tool> toolStore = new ConcurrentHashMap<>();

    static {
        initializeStore();
    }

    private ToolRepository() {
        // Private constructor to prevent instantiation
    }

    private static void initializeStore() {
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
        return toolStore.putIfAbsent(toolCode, tool) == null;
    }

    public static boolean updateTool(String toolCode, Tool tool) {
        return toolStore.replace(toolCode, tool) != null;
    }

    public static boolean deleteTool(String toolCode) {
        return toolStore.remove(toolCode) != null;
    }
}
