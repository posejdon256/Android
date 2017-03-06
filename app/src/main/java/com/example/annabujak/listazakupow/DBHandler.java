package com.example.annabujak.listazakupow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pawel.bujak on 03.03.2017.
 */

public class DBHandler extends SQLiteOpenHelper {
    //DATABASE_VERSION
    private static final int DATABASE_VERSION = 1;
    //DATABASE_NAME
    private static final String DATABASE_NAME = "shoppingList";
    //CONTACTS_TABLE
    private static final String TABLE_PRODUCTS = "products";
    //PRODUCTS_TABLE_COLUMNS_NAMES
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COUNT = "count";
    private static final String KEY_UNIT = "unit";
    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + TABLE_PRODUCTS+
                "("+KEY_ID+ " INTEGER PRIMARY KEY,"+KEY_NAME+
                " TEXT,"+ KEY_COUNT + " INTEGER,"+ KEY_UNIT+
                " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUCTS);
        onCreate(db);
    }
    //Add product
    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,product.getName());
        values.put(KEY_COUNT,product.getCount());
        values.put(KEY_UNIT,product.getUnit());
        db.insert(TABLE_PRODUCTS,null,values);
        db.close();
    }
    public Product getProduct(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PRODUCTS,new String[]{KEY_ID,KEY_NAME,KEY_COUNT,KEY_UNIT},KEY_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        Product contact = new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),
                cursor.getString(3));
        return contact;
    }
    public List<Product> getAllProducts(){
        List<Product> productsList = new ArrayList<Product>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Product p = new Product();
                p.setId(Integer.parseInt(cursor.getString(0)));
                p.setName(cursor.getString(1));
                p.setCount(Integer.parseInt(cursor.getString(2)));
                p.setUnit(cursor.getString(3));
                productsList.add(p);
            }while (cursor.moveToNext());
        }
        return productsList;
    }
    public int getProductsNumber(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        return  count;

    }
    public int updateProduct(Product p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(KEY_NAME,p.getName());
        vals.put(KEY_COUNT,p.getCount());
        vals.put(KEY_UNIT,p.getUnit());
        return db.update(TABLE_PRODUCTS,vals,KEY_ID+"=?",new String[]{String.valueOf(p.getId())});

    }
    public void deleteProduct(Product p){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, KEY_ID + "=?",new String[]{String.valueOf(p.getId())});
        db.close();
    }
}
