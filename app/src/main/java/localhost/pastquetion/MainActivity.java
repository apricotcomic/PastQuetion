package localhost.pastquetion;

import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PastQuestionsDao dao;
    private LinearLayout showData;
    private int displayYear;
    private int displayQno;
    private String displayStatement;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        // SQLiteの準備
        PastQuestionsHelper helper = new PastQuestionsHelper(this, null, 1);
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            helper.onCreate(db);
            dao = new PastQuestionsDao(db);
        }catch(SQLiteException e){
            return;
        }

        // 表示データの更新
        displayData();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // データ入力欄の初期設定
        //dataEdit = (EditText)findViewById(R.id.dataEdit);
        //dataEdit.setOnKeyListener(new AddressBarOnKeyListener());

        // データ出力Viewグループの取得
        //showData = (LinearLayout)findViewById(R.id.showData);

    }

    /**
     * 表示データの更新
     */
    private void displayData() {

        pastQuestions pastQuestions = new pastQuestions();
        // 表示中のデータを一旦すべてクリアする。
        //showData.removeAllViews();

        // DBからすべてのデータを取得する。
        int searchYear = 30;
        pastQuestions entityList = dao.findByyear(searchYear);

        // データを表示領域に追加する
        TextView displaystatement = new TextView(this);
        displayStatement = pastQuestions.getStatement();
        showData.addView(displaystatement);
    }

    // ソフトキーボードの「確定」が押された時にソフトキーボードを消す
    private class AddressBarOnKeyListener implements OnKeyListener {

        public boolean onKey(View view, int keyCode, KeyEvent event) {

            //EnterKeyが押されたかを判定
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && keyCode == KeyEvent.KEYCODE_ENTER) {

                //ソフトキーボードを閉じる
                InputMethodManager inputMethodManager =
                        (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                return true;
            }

            return false;
        }
    }

}

