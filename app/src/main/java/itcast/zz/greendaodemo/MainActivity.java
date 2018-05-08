package itcast.zz.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import itcast.zz.greendaodemo.db.DaoMaster;
import itcast.zz.greendaodemo.db.DaoSession;
import itcast.zz.greendaodemo.db.User;
import itcast.zz.greendaodemo.db.UserDao;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private UserDao userDao;
    private Button bt_add;
    private Button bt_del;
    private Button bt_updata;
    private Button bt_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // 该初始化过程最好放在Application中进行,避免创建多个Session
        /*private void setupDatabase() {
            // 通过 DaoMaster 的内部类 DevOpenHelper创建数据库
            // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表
            // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
            *//**
         * @param context :　Context
         * @param name : 数据库名字
         * @param factory : CursorFactroy
         *//*
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "student.db", null);
            // 获取数据库
            SQLiteDatabase database = helper.getWritableDatabase();
            // 获取DaoMaster
            DaoMaster daoMaster = new DaoMaster(database);
            // 获取Session
            DaoSession daoSession = daoMaster.newSession();
            // 获取对应的表的DAO对象
            InfoDao dao = daoSession.getInfoDao();
        }*/

        //创建DevOpenHelper实例
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "user.db", null);
        //获取数据库
        SQLiteDatabase writableDatabase = devOpenHelper.getWritableDatabase();
        //获取DaoMaster
        DaoMaster daoMaster = new DaoMaster(writableDatabase);
        //获取session
        DaoSession daoSession = daoMaster.newSession();
        //获取对应的表的DAO对象
        userDao = daoSession.getUserDao();

    }

    private void initView() {
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_updata = (Button) findViewById(R.id.bt_updata);
        bt_find = (Button) findViewById(R.id.bt_find);

        bt_add.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_updata.setOnClickListener(this);
        bt_find.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                add();
                break;
            case R.id.bt_del:
                del();
                break;
            case R.id.bt_updata:
                updata();
                break;
            case R.id.bt_find:
                find();
                break;
        }
    }

    /**
     * 查
     */
    private void find() {

//        userDao.queryRaw("name=?","wangwu");
        QueryBuilder<User> wangwu = userDao.queryBuilder().where(UserDao.Properties.Name.eq("wangwu"));
        List<User> list = wangwu.list();
        for (User user:list
             ) {
            System.out.println(user);
        }
    }

    /**
     * 删除
     */
    private void del() {

        User user = new User(1l);
        userDao.delete(user);
//        userDao.deleteByKey(1L);
    }

    /**
     * 更新
     */
    private void updata() {
        User user = new User(1L,"wangwu",54);
        userDao.update(user);
    }

    /**
     * 增
     */
    private void add() {
        User user= new User(null,"zhangsan",12);
        User user2= new User(null,"lisi",22);
        userDao.insert(user);
        userDao.insert(user2);
    }
}
