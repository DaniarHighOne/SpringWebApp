package ag.selm.catalogue.repository;

import ag.selm.catalogue.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*
usually simple crud ops sometimes enough but we can write request with JPQL or SQL
for example with filtering etc
 */

public interface ProductRepository extends CrudRepository<Product,Integer> {

    @Query(name = "Product.findAllByTitleIgnorCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter);

}
