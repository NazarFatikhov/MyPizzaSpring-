package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dto.ProductDTO;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.models.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component
public class ProductsRepository implements Repository<Product>{
    
    @Autowired
    private DAO<ProductDTO> dao;
    @Autowired
    private Mapper productMapper;
    @Autowired
    private Mapper productDTOMapper;
    
    @Override
    public void add(Product product){
        if (isExist(product)){
            //TODO throw ProdcutDublicateException
        }
        else if(product.getName() == null || 
                product.getPrice() == null){
            //TODO throw ModelNullFieldException
        }
        else{
            dao.save((ProductDTO) productDTOMapper.map(product));
        }
    }
    
    @Override
    public Product getByStringKey(String name){
        List<Product> products = productDTOMapper.mapList(dao.getAll());
        for(Product p : products){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    
    @Override
    public boolean isExist(Product product){
        List<Product> products = productMapper.mapList(dao.getAll());
        for (Product p : products){
            if(p.getName().equals(product.getName())){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void update(Product product, String[] fileds){
        List<ProductDTO> products = dao.getAll();
        for(ProductDTO p : products){
            if(p.getName().equals(product.getName())){
                dao.update(p, fileds);
                break;
            }
        }
    }

    @Override
    public List<Product> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Product t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdByStringKey(String name) {
        List<ProductDTO> productDtos = dao.getAll();
        for(ProductDTO productDto : productDtos){
            if(productDto.getName().equals(name)){
                return productDto.getId();
            }
        }
        return 0;
    }
    
    
}
