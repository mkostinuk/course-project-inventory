package org.example.app;


import org.example.app.model.ProductCategory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.app.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.List;
import java.util.Properties;

public class ProductRepo {
    private final SessionFactory sessionFactory;

    public ProductRepo() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "password");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/postgres");
        sessionFactory = new Configuration().addAnnotatedClass(Product.class)
                .setProperties(properties).buildSessionFactory();
    }
    public List<Product> getAll(){
        try(Session session = sessionFactory.openSession()){
            Query<Product> query = session.createQuery("from Product", Product.class);
            return query.getResultList();
        }
    }
    public Product getByTitle(String title){
        try(Session session = sessionFactory.openSession()){
            Query<Product> query = session.createQuery("from Product where title = :title", Product.class)
                    .setParameter("title",title);
            return query.uniqueResult();
        }
    }
    public int getCount(){
        try(Session session = sessionFactory.openSession()){
            Query<Integer> query = session.createQuery("select count(*) from Product", Integer.class);
            return query.uniqueResult();
        }
    }
    public int getCountByCategory(ProductCategory productCategory){
        try(Session session = sessionFactory.openSession()){
            Query<Integer> query = session
                    .createQuery("select count(*) from Product where category = :product", Integer.class)
                    .setParameter("product", productCategory.name());
            return query.uniqueResult();
        }
    }
    public void addNewProduct(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }
    }
    public void addExistProduct(String title, int quantity){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Product product = getByTitle(title);
            product.setQuantity(product.getQuantity()+quantity);
            transaction.commit();
        }
    }
    public void update(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
        }
    }
    public void delete(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(product);
            transaction.commit();
        }
    }


}
