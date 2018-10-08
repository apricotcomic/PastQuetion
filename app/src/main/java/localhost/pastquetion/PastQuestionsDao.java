package localhost.pastquetion;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PastQuestionsDao {
    // テーブルの定数
    private static final String TABLE_NAME = "judicialScrivener";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";
    private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DATA};

    // SQLiteDatabase
    private SQLiteDatabase db;

    /**
     * コンストラクタ
     * @param db
     */
    public PastQuestionsDao(SQLiteDatabase db) {
        this.db = db;
    }

    /**
     * 全データの取得   ----------------①
     * @return
     */
    public List<pastQuestions> findAll() {
        List<pastQuestions> entityList = new  ArrayList<pastQuestions>();
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                null,
                null,
                null,
                null,
                COLUMN_ID);

        while(cursor.moveToNext()) {
            pastQuestions entity = new pastQuestions();
            entity.setId(cursor.getInt(0));
            entityList.add(entity);
        }

        return entityList;
    }

    /**
     * 特定IDのデータを取得   ----------------②
     * @param
     * @return
     */
    public pastQuestions findByyear(int year) {
        String selection = "year=?";
        String key = "30";
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMNS,
                selection,
                new String[] {key} ,
                null,
                null,
                null);

        cursor.moveToNext();
        pastQuestions entity = new pastQuestions();
        entity.setId(cursor.getInt(0));

        return entity;
    }

    /**
     * データの登録   ----------------③
     * @param
     * @return
     */
    public long insert(String value) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, value);
        return db.insert(TABLE_NAME, null, values);
    }

    /**
     * データの更新   ----------------④
     * @param
     * @param
     * @return
     */
    public int update(pastQuestions entity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, entity.getStatement());
        String whereClause = COLUMN_ID + "=" + entity.getId();
        return db.update(TABLE_NAME, values, whereClause, null);
    }

    /**
     * データの削除   ----------------⑤
     * @param rowId
     * @return
     */
    public int delete(int rowId) {
        String whereClause = COLUMN_ID + "=" + rowId;
        return db.delete(TABLE_NAME, whereClause, null);
    }
}
