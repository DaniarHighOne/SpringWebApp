package ag.selm.catalogue.repository;

//todo register component inside apllication with stereotypes
//this annotation will help with AOP

import ag.selm.catalogue.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryProductRepository implements ProductRepository{

    //better use this for concurrency
    private final List<Product> productList = Collections.synchronizedList(new LinkedList<>());

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(this.productList);
    }

    @Override
    public Product save(Product product) {
        product.setId(this.productList.stream()
                .max(Comparator.comparingInt(Product::getId))
                .map(Product::getId)
                .orElse(0) + 1);
        this.productList.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return this.productList.stream()
                .filter(product -> Objects.equals(productId, product.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        this.productList.removeIf(product -> Objects.equals(id, product.getId()));
    }

}
