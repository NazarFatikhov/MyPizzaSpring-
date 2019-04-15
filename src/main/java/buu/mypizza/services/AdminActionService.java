package buu.mypizza.services;

import buu.mypizza.models.Product;
import buu.mypizza.repositorys.ProductsRepository;
import buu.mypizza.repositorys.Repository;
import buu.mypizza.repositorys.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nazar
 */
public class AdminActionService {

    public void addProductBalance(String productName, int add){
        ApplicationContext context = new ClassPathXmlApplicationContext("cn.buu\\context.xml");
        Repository repo = context.getBean(ProductsRepository.class);
        Product product = (Product) repo.getByStringKey(productName);
        int endingPrice = product.getBalance() + add;
        String[] fiedls = {product.getName(), "" + product.getPrice(), "" + endingPrice}; 
        repo.update(product, fiedls);
    }
}
