import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DatabaseManagement {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");


    public List<SpaceShip> showShips() {

        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT s FROM SpaceShip s");
        return query.getResultList();

    }

}
