package utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("jpa-pu");
    }

    private JPAUtil() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return factory;
    }
}
