package buu.mypizza.services;

import buu.mypizza.config.AppConfig;
import buu.mypizza.models.Product;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import buu.mypizza.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component
public class AdminActionService {

    @Autowired
    Repository productsRepository;
    
    public void addProductBalance(String productName, int add){
        Product product = (Product) productsRepository.getByStringKey(productName);
        int endingPrice = product.getBalance() + add;
        String[] fiedls = {product.getName(), "" + product.getPrice(), "" + endingPrice}; 
        productsRepository.update(product, fiedls);
    }
}
