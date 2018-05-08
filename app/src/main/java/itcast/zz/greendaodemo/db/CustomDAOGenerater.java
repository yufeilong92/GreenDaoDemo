package itcast.zz.greendaodemo.db;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by XIAOQIANG on 2016/11/22.
 */
public class CustomDAOGenerater {

    public static void main(String[] args) {
        //创建schema对象
        Schema schema = new Schema(1,"itcast.zz.greendaodemo.db");
        //创建实体对象,User类
        Entity user = schema.addEntity("User");
        user.addIdProperty();  //字段为id
        user.addStringProperty("name");
        user.addIntProperty("age");

        ///生成数据库相关类
        try {
            //参数2:自动生成的路径(包名)
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
