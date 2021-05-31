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
        return "Spaceship{" +
                "modelName='" + modelName + '\'' +
                ", price=" + price +
                ", tonnage=" + tonnage +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }
}
