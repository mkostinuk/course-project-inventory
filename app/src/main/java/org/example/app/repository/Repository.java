package org.example.app.repository;

import org.example.app.model.ExportProduct;
import org.example.app.model.ImportProducts;
import org.example.app.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public abstract class Repository<T> {
    protected final SessionFactory sessionFactory;

    protected Repository(){
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "password");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        sessionFactory = new Configuration().addAnnotatedClass(Product.class)
                .addAnnotatedClass(ExportProduct.class).addAnnotatedClass(ImportProducts.class)
                .setProperties(properties).buildSessionFactory();
    }
    public abstract void save(T t);
}
