package com.example.test12savesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button but1, but2, but3;
    EditText entName, entEmale;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = findViewById(R.id.but1);
        but1.setOnClickListener(this);

        but2 = findViewById(R.id.but2);
        but2.setOnClickListener(this);

        but3 = findViewById(R.id.but3);
        but3.setOnClickListener(this);

        entName = findViewById(R.id.enterText1);
        entEmale = findViewById(R.id.enterText2);

        dbHelper = new DBHelper(this);                                                       //создание переменной, для доступа к SQL таблице

    }

    @Override
    public void onClick(View view) {
        String name = entName.getText().toString();
        String emale = entEmale.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();                                   //создаётся объект для управления базой SQL

        ContentValues contentValues = new ContentValues();                                          //переменная предоставляет собой строки базы данных

        switch (view.getId()) {

            case R.id.but1:
                contentValues.put(DBHelper.KEY_NAME, name);                                         //готовим строку с именем KEY_NAME и значением name
                contentValues.put(DBHelper.KEY_MAIL, emale);                                        //готовим строку с именем KEY_MAIL и значением emale
                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);         //записываем подготовленные строки в таблицу БД с именем TABLE_CONTACTS
                break;

                case R.id.but2:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS,                             //вызывается класс, для чтения таблицы TABLE_CONTACTS из БД
                        null, null, null, null, null, null);
                if (cursor.moveToFirst()) {                                                         //ставим курсор в начало таблицы и двигаемся поэлемнтно вперёд
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);                           //получаем индекс у значения KEY_ID
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);                       //получаем индекс у значения KEY_NAME
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);                      //получаем индекс у значения KEY_MAIL
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +                  //выводим значения в лог
                                           ", name = " + cursor.getString(nameIndex) +
                                          ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());                                                  //повторяем действия с тиблицей
                }else
                    Log.d("mLog", "0 rows");                                               //сообщение, если БД ещё не заполнялась

                cursor.close();                                                                     //закрытие класса на чтение, для освобождения памяти
                break;

                case R.id.but3:
                    database.delete(DBHelper.TABLE_CONTACTS, null, null);       //удаляем все данные в таблице БД
                break;
        }
        dbHelper.close();                                                                           //закрываем доступ к БД
    }
}