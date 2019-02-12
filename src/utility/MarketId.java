package utility;

public enum MarketId {

    IT("APJ6JRA9NG5V4"),
    ES("A1RKKUPIHCS9HS"),
    FR("A13V1IB3VIYZZH"),
    DE("A1PA6795UKMFR9"),
    GB("A1F83G8C2ARO7P"),
    APJ6JRA9NG5V4("IT"),
    A1RKKUPIHCS9HS("ES"),
    A13V1IB3VIYZZH("FR"),
    A1PA6795UKMFR9("DE"),
    A1F83G8C2ARO7P(" GB");

    private String text;

    MarketId(String text) {
        this.text = text;
    }

    public static MarketId fromString(String text) {
        for (MarketId id_name : MarketId.values()) {
            if (id_name.text.equalsIgnoreCase(text)) {
                return id_name;
            }
        }
        return null;
    }

    public String getText() {
        return this.text;
    }
}
