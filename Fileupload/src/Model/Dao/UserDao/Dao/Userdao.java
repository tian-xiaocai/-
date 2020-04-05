package Model.Dao.UserDao.Dao;

import Model.Dao.UserDao.bean.user;

import java.util.List;

public interface Userdao {
    List<user> findAll();
}
