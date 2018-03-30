package com.cs386.NAUToDo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tony on 11/25/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String ACCOUNT_TABLE = "account";
    private static String ACCOUNT_COL0 = "accountID";
    private static String ACCOUNT_COL1 = "accountUserName";
    private static String ACCOUNT_COL2 = "accountPassword";
    private static String ACCOUNT_COL3 = "accountEmail";
    private static String DECLARE_ACCOUNT = "CREATE TABLE IF NOT EXISTS "+ACCOUNT_TABLE+"("+
            ACCOUNT_COL0+" INT, "+
            ACCOUNT_COL1+" TEXT, "+
            ACCOUNT_COL2+" TEXT, "+
            ACCOUNT_COL3+" TEXT);";
    private static String ACCOUNT_ZERO = "INSERT INTO account VALUES(10,'UserName','Password','coolguy@gmail.com');";

    private static String LISTS_TABLE = "lists";
    private static String LISTS_COL0 = "listID";
    private static String LISTS_COL1 = "accountFK";
    private static String LISTS_COL2 = "name";
    //private static String LISTS_COL3 = "store";
    private static String DECLARE_LISTS = "CREATE TABLE IF NOT EXISTS "+LISTS_TABLE+"("+
            LISTS_COL0+" INT, "+
            LISTS_COL1+" INT, "+
            LISTS_COL2+" TEXT);";
    private static String LISTS_ZERO = "INSERT INTO lists VALUES(0,10,'computerHardware');";

    private static String ITEMS_TABLE = "item";
    private static String ITEMS_COL0 = "itemID";
    private static String ITEMS_COL1 = "listFK";
    private static String ITEMS_COL2 = "name";
    private static String ITEMS_COL3 = "quantity";
    private static String DECLARE_ITEMS = "CREATE TABLE IF NOT EXISTS "+ITEMS_TABLE+"("+
            ITEMS_COL0+" INT, "+
            ITEMS_COL1+" INT, "+
            ITEMS_COL2+" TEXT, " +
            ITEMS_COL3+" INT); ";
    private static String ITEMS_ZERO = "INSERT INTO item VALUES(0,0,'CPU',1);";

    public DataBaseHelper(Context context)
    {
        super(context,ACCOUNT_TABLE,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DECLARE_LISTS);
        db.execSQL(LISTS_ZERO);

        db.execSQL(DECLARE_ACCOUNT);
        db.execSQL(ACCOUNT_ZERO);

        db.execSQL(DECLARE_ITEMS);
        db.execSQL(ITEMS_ZERO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+LISTS_TABLE);
        onCreate(db);
    }
    public void CLEARACCOUNTS()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNT_TABLE);
        onCreate(db);
    }
    public void CLEARLISTS()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+LISTS_TABLE);
        onCreate(db);
    }
    public void CLEARITEMS()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ITEMS_TABLE);
        onCreate(db);
    }
    public String LOGIN(String EnteredUserName,String EnteredPassword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ACCOUNT_TABLE+" WHERE "+
                ACCOUNT_COL1+" = '"+EnteredUserName +"' AND "+
                ACCOUNT_COL2+" = '"+EnteredPassword +"'; ",null);
        String id = "-1";
        while(data.moveToNext())
        {
            id = data.getString(0);
        }
        return id;
    }

    public boolean ADDLIST(String accountPK,String EnteredListName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO lists VALUES("+MAXLISTPK()+1+","+accountPK+",'"+EnteredListName+"');";
        db.execSQL(query);
        return true;
    }
    public boolean ADDITEM(String accountPK,String ListPK,String EnteredListName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO item VALUES("+MAXITEMPK()+1+","+ListPK+",'"+EnteredListName+"',1);";
        db.execSQL(query);
        return true;
    }
    public String GETLISTPK(String accountPK,String listName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE+" WHERE "+
                LISTS_COL1+" = "+accountPK +" AND "+
                LISTS_COL2+" = '"+listName +"'; ",null);
        String id = "-1";
        while(data.moveToNext())
        {
            id = data.getString(0);
        }
        return id;
    }
    public String GETITEMPK(String ItemName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ITEMS_TABLE+" WHERE "+
                ITEMS_COL2+" = '"+ItemName +"';",null);
        String id = "-1";
        while(data.moveToNext())
        {
            id = data.getString(0);
        }
        return id;
    }
    public String GETITEMNAME(String itemID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ITEMS_TABLE+" WHERE "+
                ITEMS_COL0+" = "+itemID +";",null);
        String id = "Error";
        while(data.moveToNext())
        {
            id = data.getString(2);
        }
        return id;
    }
    public int GETITEMQUANTITY(String itemID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ITEMS_TABLE+" WHERE "+
                ITEMS_COL0+" = "+itemID +";",null);
        int id = -1;
        while(data.moveToNext())
        {
            id = data.getInt(3);
        }
        return id;
    }
    public String INCITEMQUANTITY(String itemID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Update "+ITEMS_TABLE+" SET "+
                ITEMS_COL3+" = "+(GETITEMQUANTITY(itemID)+1)+" WHERE "+
                ITEMS_COL0+" = "+itemID +";",null);
        String id = "Error";
        while(data.moveToNext())
        {
            id = data.getString(3);
        }
        return id;
    }
    public String DECITEMQUANTITY(String itemID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Update "+ITEMS_TABLE+" SET "+
                ITEMS_COL3+" = "+(GETITEMQUANTITY(itemID)-1)+" WHERE "+
                ITEMS_COL0+" = "+itemID +";",null);
        String id = "Error";
        while(data.moveToNext())
        {
            id = data.getString(3);
        }
        return id;
    }
    public String RENAMEITEM(String itemID,String newname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("UPDATE "+ITEMS_TABLE+" SET "+
                ITEMS_COL2+" = '"+newname+"' WHERE "+
                ITEMS_COL0+" = "+itemID +";",null);
        String id = "Error";
        while(data.moveToNext())
        {
            id = data.getString(2);
        }
        return id;
    }
public int MAXACCOUNTPK()
{
    SQLiteDatabase db = this.getWritableDatabase();
    //Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
    Cursor data = db.rawQuery("SELECT * FROM "+ACCOUNT_TABLE,null);
    int id = -1;
    while(data.moveToNext())
    {
        id = data.getInt(0);
    }
    return id;
}
    public int MAXLISTPK()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
        Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
        int id = -1;
        while(data.moveToNext())
        {
            id = data.getInt(0);
        }
        return id;
    }
    public int MAXITEMPK()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
        Cursor data = db.rawQuery("SELECT * FROM "+ITEMS_TABLE,null);
        int id = -1;
        while(data.moveToNext())
        {
            id = data.getInt(0);
        }
        return id;
    }
    public boolean ADDACCOUNT(String EnteredUserName,String EnteredPassword,String EnteredEmail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ACCOUNT_COL0,MAXACCOUNTPK()+1);
        contentValues.put(ACCOUNT_COL1,EnteredUserName);
        contentValues.put(ACCOUNT_COL2,EnteredPassword);
        contentValues.put(ACCOUNT_COL3,EnteredEmail);

        long result = db.insert(ACCOUNT_TABLE,null,contentValues);
        return true;
        //if (LOGIN(EnteredUserName,EnteredPassword)=="-1")
        //{
        //    return false;
        //}else {
        //    return true;
        //}
    }
    public Cursor ACCOUNTLISTS(String accountPK)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
        Cursor data = db.rawQuery("SELECT "+LISTS_COL2+" FROM "+LISTS_TABLE+" WHERE "+ LISTS_COL1+" = "+accountPK,null);
        return data;
    }
    public Cursor ListID(String itemname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor data = db.rawQuery("SELECT * FROM "+LISTS_TABLE,null);
        Cursor data = db.rawQuery("SELECT "+LISTS_COL2+" FROM "+LISTS_TABLE+" WHERE "+ LISTS_COL2+" = "+itemname,null);
        return data;
    }
    public Cursor ITEMLISTS(String listPK)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ITEMS_TABLE+" WHERE "+ ITEMS_COL1 +" = "+listPK,null);
        return data;
    }

    public void INIT() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ACCOUNT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+LISTS_TABLE);
        onCreate(db);
    }
}
