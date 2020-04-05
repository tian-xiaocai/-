package Controller.Service;


import Model.Dao.GoodsDao.bean.Goods;

import java.util.List;

public class GoodService {
    Model.Dao.GoodsDao.dao.DaoGoods daoGoods= (Model.Dao.GoodsDao.dao.DaoGoods) new Model.DaoImplements.goodsImp();
    public void add(Model.Dao.GoodsDao.bean.Goods goods){

        daoGoods.add(goods);
    }
    public List<Goods> findAll(){
       return daoGoods.findAll();
    }
}
