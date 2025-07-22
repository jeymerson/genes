package org.jey;

public class ConsoleColors {
    public static final String RESET  = "\033[0m";     // Reset de cor
    public static final String BOLD   = "\033[1m";     // Texto em negrito

    public static final String GREEN  = "\033[0;32m";  // Nível A1
    public static final String YELLOW = "\033[0;33m";  // Nível A2
    public static final String CYAN   = "\033[0;36m";  // Nível B1
    public static final String BLUE   = "\033[0;34m";  // Nível B2
    public static final String PURPLE = "\033[0;35m";  // Nível C1
    public static final String RED    = "\033[0;31m";  // Nível C2

    public static String getLevelColor(String nivel) {
        return switch (nivel.toUpperCase()) {
            case "1", "A1" -> GREEN;
            case "2", "A2" -> YELLOW;
            case "3", "B1" -> CYAN;
            case "4", "B2" -> BLUE;
            case "5", "C1" -> PURPLE;
            case "6", "C2" -> RED;
            default -> RESET;
        };
    }
}
