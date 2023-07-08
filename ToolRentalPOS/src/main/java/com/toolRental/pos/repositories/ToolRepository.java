package com.toolRental.pos.repositories;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.toolRental.pos.models.Tool;

/**
 * Repository class for managing tools.
 */
public class ToolRepository {
    private static final Map<String, Tool> toolStore = new ConcurrentHashMap<>();

    /**
     * Static block to initialize the tool store with default tools.
     */
    static {
        initializeStore();
    }

    private ToolRepository() {
        // Private constructor to prevent instantiation
    }

    /**
     * Initializes the tool store with default tools.
     */
    private static void initializeStore() {
        toolStore.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99"), true, false, false));
        toolStore.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolStore.put("LADW", new Tool("LADW", "Ladder", "Werner", new BigDecimal("1.99"), true, true, false));
        toolStore.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49"), true, false, true));
    }

    /**
     * Retrieves a tool by its code.
     *
     * @param toolCode the code of the tool
     * @return the tool with the specified code
     * @throws IllegalArgumentException if the tool code is invalid
     */
    public static Tool getTool(String toolCode) {
        if (!toolStore.containsKey(toolCode)) {
            throw new IllegalArgumentException("Invalid tool code: " + toolCode);
        }
        return toolStore.get(toolCode);
    }

    /**
     * Adds a new tool to the store.
     *
     * @param toolCode the code of the tool
     * @param tool     the tool to be added
     * @return true if the tool was added successfully, false if a tool with the same code already exists
     */
    public static boolean addTool(String toolCode, Tool tool) {
        return toolStore.putIfAbsent(toolCode, tool) == null;
    }

    /**
     * Updates an existing tool in the store.
     *
     * @param toolCode the code of the tool to be updated
     * @param tool     the updated tool
     * @return true if the tool was updated successfully, false if the tool does not exist in the store
     */
    public static boolean updateTool(String toolCode, Tool tool) {
        return toolStore.replace(toolCode, tool) != null;
    }

    /**
     * Deletes a tool from the store.
     *
     * @param toolCode the code of the tool to be deleted
     * @return true if the tool was deleted successfully, false if the tool does not exist in the store
     */
    public static boolean deleteTool(String toolCode) {
        return toolStore.remove(toolCode) != null;
    }
}
