package com.zhpush.client.db;

import android.content.Context;

import com.cjz.clog.CLog;
import com.zhpush.client.pojo.MixPushMessage;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class MsgDbManager {
    private static final String TAG = MsgDbManager.class.getSimpleName();

    private static volatile MsgDbManager instance;

    private DaoSession daoSession;
    private Context mContext;
    public static boolean isInit = false;

    private MsgDbManager() {
        super();
    }

    public static MsgDbManager getInstance() {
        if (instance == null) {
            synchronized (MsgDbManager.class) {
                if (instance == null) {
                    instance = new MsgDbManager();
                }
            }
        }
        return instance;
    }


    public void init(Context context) {
        mContext = context;
        try {
            DBOpenHelper helper = new DBOpenHelper(mContext, "MixMsgDb.db");
            //获取可写数据库
            Database db = helper.getWritableDb();
            if (db != null) {
                //获取数据库对象
                DaoMaster daoMaster = new DaoMaster(db);
                //获取Dao对象管理者
                daoSession = daoMaster.newSession();
                if (daoSession!=null){
                    isInit = true;
                }
            }
        } catch (Exception e) {
            CLog.e(e.toString());
        }
    }

    public static class DBOpenHelper extends DaoMaster.OpenHelper {
        public DBOpenHelper(Context context, String name) {
            super(context, name);
        }
    }

    public synchronized DaoSession getDaoSession() {
        if (daoSession == null) {
            init(mContext);
        }
        return daoSession;
    }


    public boolean isMsgExist(MixPushMessage msg) {
        MixPushMessageDao dao = getDaoSession().getMixPushMessageDao();
        if (dao != null) {
            List<MixPushMessage> list = dao.queryBuilder().where(MixPushMessageDao.Properties.MessageId.eq(msg.getMessageId())).list();
            if (list != null && list.size() > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public void saveMsg(MixPushMessage msg) {
        MixPushMessageDao dao = getDaoSession().getMixPushMessageDao();
        if (dao != null) {
            dao.save(msg);
        }
    }

    public void cleanCache() {
        MixPushMessageDao dao = getDaoSession().getMixPushMessageDao();
        if (dao != null) {
            dao.deleteAll();
        }
    }


}
