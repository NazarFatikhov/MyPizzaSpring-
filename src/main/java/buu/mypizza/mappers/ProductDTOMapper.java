package buu.mypizza.mappers;

import buu.mypizza.dto.ProductDTO;
import buu.mypizza.models.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component
public class ProductDTOMapper implements Mapper<ProductDTO, Product>{

    @Override
    public Product map(ProductDTO dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getBalance());
        return product;
    }
    
    public List<Product> mapList(List<ProductDTO> productsOrders){
        List<Product> products = new ArrayList<>();
        for (ProductDTO dto : productsOrders){
            Product product = new Product(dto.getName(), dto.getPrice(), dto.getBalance());
            products.add(product);
        }
        return products;
    }
    
}
