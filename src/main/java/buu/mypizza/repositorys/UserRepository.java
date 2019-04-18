package buu.mypizza.repositorys;

import buu.mypizza.dao.DAO;
import buu.mypizza.dao.UserDAO;
import buu.mypizza.dto.UserDTO;
import buu.mypizza.mappers.Mapper;
import buu.mypizza.mappers.UserDTOMapper;
import buu.mypizza.mappers.UserMapper;
import buu.mypizza.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author nazar
 */
@Component   
public class UserRepository implements Repository<User>{
    
    @Autowired
    DAO<UserDTO> userDao;
    @Autowired
    private Mapper userMapper;
    @Autowired
    private Mapper userDTOMapper;
    
    @Override
    public void add(User user){
        userDao.save((UserDTO) userMapper.map(user));
    }
    
    public User getByStringKey(String email){
        List<User> users = userDTOMapper.mapList(userDao.getAll());
        for(User u : users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    
    @Override
    public boolean isExist(User user){
        List<User> users;
        users = userDTOMapper.mapList(userDao.getAll());
        for (User u : users){
            if(user.getEmail().equals(u.getEmail())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(User t, String[] fields) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getIdByStringKey(String email){
        List<UserDTO> usersDto = userDao.getAll();
        for(UserDTO userDto : usersDto){
            if(userDto.getEmail().equals(email)){
                return userDto.getId();
            }
        }
        return 0;
    }
}
