package localhost.pastquetion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class PastQuestionsHelper  extends SQLiteOpenHelper {

    private static final String DB_NAME = "judicialScrivener";
    private static final String TABLE_NAME = "pastQuestions";

    /** 「dbtest」テーブルの作成用SQL */
    private static final String CREATE_TABLE_SQL = "" +
            "create table " + TABLE_NAME + " (" +
            "_id integer primary key autoincrement, " +
            "year integer not null," +
            "ampm text not null," +
            "qno integer not null," +
            "limbs integer not null," +
            "statement text," +
            "answer text not null " +
            ")";

    /** 「dbtest」テーブルの削除用SQL */
    private static final String DROP_TABLE_SQL = "drop table if exists " + TABLE_NAME;

    public PastQuestionsHelper(
            Context context,
            CursorFactory factory,
            int version) {

        super(context, DB_NAME, factory, version);
    }

    /**
     * テーブルの生成（必須）
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);

        ContentValues values = new ContentValues();
        values.put("year",30);
        values.put("ampm","am");
        values.put("qno",1);
        values.put("limbs",1);
        values.put("statement","問題文");
        values.put("answer",1);

        db.insert(DB_NAME,null,values);
    }

    /**
     * テーブルの再作成（必須）
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_SQL);
        db.execSQL(CREATE_TABLE_SQL);
    }
}
