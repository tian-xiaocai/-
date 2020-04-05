package Model.DaoImplements;

import Model.Dao.GoodsDao.bean.Goods;
import Model.Dao.GoodsDao.dao.DaoGoods;
import Model.JDBC.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class goodsImp implements DaoGoods {

    @Override
    public void add(Goods goods) {
        String sql="insert into goods(name,price,img) values(?,?,?)";//有可能的错误
        PreparedStatement ps=null;
        try {
            ps = JDBCUtils.getConnection().prepareStatement(sql);
            ps.setString(1,goods.getName());
            ps.setDouble(2, goods.getPrice());
            ps.setString(3,goods.getImg());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<Goods> findAll() {
        List<Goods> list=new ArrayList<>();
        String sql="select * from goods";//有可能的错误
        PreparedStatement ps=null;
        try {
            ps = JDBCUtils.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Goods goods = new Goods(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4));
                list.add(goods);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
