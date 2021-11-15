package baseline;

public class Item {

    private String name;
    private String serialNumber;
    private double cost;

    public Item(String name, String serialNumber, String cost) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.cost = Integer.parseInt(cost);
    }
}
