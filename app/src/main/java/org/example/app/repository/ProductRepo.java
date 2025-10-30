package org.example.app.repository;


import org.example.app.model.ProductCategory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.example.app.model.Product;
import org.hibernate.Session;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepo extends Repository<Product> {
    private static ProductRepo instance;


    public static ProductRepo getInstance() {
        if(instance==null){
            instance = new ProductRepo();
        }
        return instance;
    }
    public Optional<Product> findById(UUID id){
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.get(Product.class, id));
        }
    }
    public List<Product> getAll(){
        try(Session session = sessionFactory.openSession()){
            Query<Product> query = session.createQuery("from Product", Product.class);
            return query.getResultList();
        }
    }
    public Optional<Product> getByTitle(String title){
        try(Session session = sessionFactory.openSession()){
            Query<Product> query = session.createQuery("from Product where title = :title", Product.class)
                    .setParameter("title",title);
            return Optional.ofNullable(query.uniqueResult());
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
    public void save(Product product){
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }
    }
    public void saveExist(String title, int quantity){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Product product = getByTitle(title).orElseThrow(RuntimeException::new);
            product.setQuantity(product.getQuantity()+quantity);
            session.merge(product);
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


    public boolean existByTitle(String title) {
        return getByTitle(title).isPresent();
    }
}
