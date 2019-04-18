package buu.mypizza.services;

import buu.mypizza.config.AppConfig;
import buu.mypizza.models.Order;
import buu.mypizza.models.User;
import buu.mypizza.models.Product;
import buu.mypizza.repositorys.OrderRepository;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import buu.mypizza.repositorys.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component
public class OrderActionService {
    
    @Autowired
    Repository orderRepository;
    @Autowired
    Repository productsRepository;
    @Autowired
    Repository userRepository;

    public void createOrder(String emailUser, Map<String, Integer> products, String address, String comment){
        User owner = (User) userRepository.getByStringKey(emailUser);
        List<Product> productsList = new ArrayList<>();
        int priceOfAllProducts = 0;
        for (String productName : products.keySet()){
            Product product = (Product) productsRepository.getByStringKey(productName);
            String[] newProductFields = {product.getName(), Double.toString(product.getPrice()), Integer.toString(product.getBalance() - products.get(productName))};
            productsRepository.update(product, newProductFields);
            priceOfAllProducts += products.get(productName) * product.getPrice();
            productsList.add(product);
        }
        Order order = new Order(owner, productsList, priceOfAllProducts, Date.from(Instant.now()), address, comment);
        orderRepository.add(order);
    }
    
    public double getPriceForProduct(String productName){
        Product product = (Product) productsRepository.getByStringKey(productName);
        return product.getPrice();
    }
    
}
