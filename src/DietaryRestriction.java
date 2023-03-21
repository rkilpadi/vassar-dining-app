public record DietaryRestriction(String restriction, String imagePath) {
    public String toString() {
        return switch (restriction) {
            case "vegetarian" -> "(V)";
            case "vegan" -> "(VG)";
            default -> "";
        };
    }
}
