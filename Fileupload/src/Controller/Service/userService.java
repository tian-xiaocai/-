package Controller.Service;

import Model.Dao.UserDao.UserImp.userImp;
import Model.Dao.UserDao.bean.user;

import java.util.List;

public class userService {
    Model.Dao.UserDao.UserImp.userImp userImp = new userImp();
    public List<user> findAll(){
        List<user> all = userImp.findAll();
        return all;
    }
}
