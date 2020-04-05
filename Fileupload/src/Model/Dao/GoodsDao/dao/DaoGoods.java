package Model.Dao.GoodsDao.dao;

import Model.Dao.GoodsDao.bean.Goods;

import java.util.List;

public interface DaoGoods {
    void add(Goods goods);
    List<Goods> findAll();
}
