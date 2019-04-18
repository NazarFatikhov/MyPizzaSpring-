package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.OrderDAO;
import buu.mypizza.dao.ProductDAO;
import buu.mypizza.dao.ProductsOrdersDAO;
import buu.mypizza.dao.UserDAO;
import buu.mypizza.dto.OrderDTO;
import buu.mypizza.dto.ProductDTO;
import buu.mypizza.dto.ProductsOrdersDTO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.models.User;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.mappers.BiMapper;
import buu.mypizza.mappers.OrderMapper;
import buu.mypizza.mappers.OrderDTOMapper;
import buu.mypizza.mappers.ProductDTOMapper;
import buu.mypizza.mappers.ThreeMapper;
import buu.mypizza.mappers.ProductMapper;
import buu.mypizza.models.Order;
import buu.mypizza.models.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component
public class OrderRepository implements Repository<Order>{

    @Autowired
    DAO<OrderDTO> orderDAO;
    @Autowired
    DAO<ProductsOrdersDTO> productsOrdersDAO;
    @Autowired
    DAO<ProductDTO> productDAO;
    @Autowired
    DAO<UserDTO> userDAO;
    @Autowired
    Mapper productDTOMapper;
    @Autowired    
    ThreeMapper orderMapper;
    @Autowired
    Repository userRepository;
    @Autowired        
    Repository productsRepository;
    @Autowired    
    BiMapper orderDTOMapper;

    @Override
    public boolean isExist(Order order) {
        List<OrderDTO> orderDtos = orderDAO.getAll();
        for(OrderDTO orderDto : orderDtos){
            if(orderDto.getId() == order.getOrdeNum()){
                return true;
            }
        }
        return false;
    }
    
    private Order getOrder(OrderDTO orderDto){
        UserDTO user = (UserDTO) userDAO.get(orderDto.getUserId()).get();
        List<ProductDTO> productDtos = new ArrayList<>();
        List<ProductDTO> allProductDtos = productDAO.getAll();
        List<ProductsOrdersDTO> poDtos = productsOrdersDAO.getAll();
        for(ProductDTO productDto : allProductDtos){
            for(ProductsOrdersDTO poDto : poDtos){
                if(productDto.getId() == poDto.getProductId() && orderDto.getId() == poDto.getOrderId()){
                    productDtos.add(productDto);
                    break;
                }
            }
        }
        Order order = (Order) orderMapper.map(orderDto, productDtos, user);
        if(isExist(order)){
            return order;
        }
        else{
            return null;
        }
    }

    private int getLastOrderNum(){
        List<OrderDTO> orderDtos = orderDAO.getAll();
        List<Integer> ids = new ArrayList<>();
        for(OrderDTO orderDto: orderDtos){
            ids.add(orderDto.getId());
        }
        int maxId = Collections.max(ids);
        return maxId;
    }
    
    @Override
    public Order getByStringKey(String orderNum) {
        Order order = null;
        int ordNum = Integer.parseInt(orderNum);
        List<OrderDTO> orders = orderDAO.getAll();
        ThreeMapper mapper = new OrderMapper();
        for(OrderDTO orderDto : orders){
            if(orderDto.getId() == ordNum){
                order = getOrder(orderDto);
                break;
            }
        }
        return order;
    }

    @Override
    public void add(Order order) {
        order.setOrdeNum(getLastOrderNum() + 1);
        int userId = userRepository.getIdByStringKey(order.getOwner().getEmail());
        OrderDTO orderDto = (OrderDTO) orderDTOMapper.map(order, userId);
        orderDAO.save(orderDto);
        for (Product product : order.getProducts()) {
            ProductsOrdersDTO poDto = new ProductsOrdersDTO(order.getOrdeNum(), productsRepository.getIdByStringKey(product.getName()));
            productsOrdersDAO.save(poDto);
        }
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Order t, String[] fields) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getIdByStringKey(String orderNum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
