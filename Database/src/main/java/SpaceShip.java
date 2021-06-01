import javax.persistence.*;


@Entity
public class SpaceShip {

    @Id
    @Column(name = "modelName")
    String modelName;

    @Column(name = "price")
    int price;

    @Column(name = "tonnage")
    int tonnage;

    @Column(name = "passengerCapacity")
    int passengerCapacity;

    public SpaceShip(String modelName, int price, int tonnage, int passengerCapacity) {
        this.modelName = modelName;
        this.price = price;
        this.tonnage = tonnage;
        this.passengerCapacity = passengerCapacity;
    }

    public SpaceShip() {

    }

    public SpaceShip(Request req) {

        if (req.getUrlParams().containsKey("modelName")) {
            modelName = req.getUrlParams().get("modelName");
        } else {
            throw new RuntimeException("Invalid Input Parameters. Please Refer To Root Page For Readme");
        }

        if (req.getUrlParams().containsKey("price")) price = Integer.parseInt(req.getUrlParams().get("price"));
        if (req.getUrlParams().containsKey("tonnage")) tonnage = Integer.parseInt(req.getUrlParams().get("tonnage"));
        if (req.getUrlParams().containsKey("passengerCapacity"))
            passengerCapacity = Integer.parseInt(req.getUrlParams().get("passengerCapacity"));
    }

    public String getModelName() {
        return modelName;
    }

    public int getPrice() {
        return price;
    }

    public int getTonnage() {
        return tonnage;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public String toString() {
        return "Spaceships In Stock: " +
                "\nModel Name: " + modelName + '\'' +
                "\nPrice: " + price +
                "\nTonnage: " + tonnage +
                "\nPassenger Capacity: " + passengerCapacity;
    }
}
