package com.rsnorrena.lotto64fun;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper
{
private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
//destination path (location) of our database on device
private static String DB_PATH = ""; 
private static String DB_NAME ="Lotto64fundbv1";// Database name
private SQLiteDatabase mDataBase; 
private final Context mContext;

public DataBaseHelper(Context context) 
{
    super(context, DB_NAME, null, 1);// 1? its Database Version
    if(android.os.Build.VERSION.SDK_INT >= 17){
       DB_PATH = context.getApplicationInfo().dataDir + "/databases/";         
    }
    else
    {
       DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }
    this.mContext = context;
}   

public void createDataBase() throws IOException
{
    //If database not exists copy it from the assets

    boolean mDataBaseExist = checkDataBase();
    if(!mDataBaseExist)
    {
    	SQLiteDatabase.loadLibs(mContext);
        this.getReadableDatabase("");
        this.close();
        try 
        {
            //Copy the database from assests
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        } 
        catch (IOException mIOException) 
        {
            throw new Error("ErrorCopyingDataBase");
        }
    }
}
    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //Open the database, so we can query it
    public boolean openDataBase() throws SQLException
    {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, "password", SQLiteDatabase.CREATE_IF_NECESSARY);
        SQLiteDatabase.loadLibs(mContext);
        mDataBase = SQLiteDatabase.openOrCreateDatabase(mPath, "@Override", null);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() 
    {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

//	public void encrypt() throws IOException {
//		// TODO Auto-generated method stub
//		SQLiteDatabase.loadLibs(mContext);
//		File originalFile=mContext.getDatabasePath(DB_NAME);
//
//	    if (originalFile.exists()) {
//	      File newFile=
//	          File.createTempFile("sqlcipherutils", "tmp",
//	        		  mContext.getCacheDir());
//	      SQLiteDatabase db=
//	          SQLiteDatabase.openDatabase(originalFile.getAbsolutePath(),
//	                                      "", null,
//	                                      SQLiteDatabase.OPEN_READWRITE);
//
//	      db.rawExecSQL(String.format("ATTACH DATABASE '%s' AS encrypted KEY '%s';",
//	                                  newFile.getAbsolutePath(), "@Override"));
//	      db.rawExecSQL("SELECT sqlcipher_export('encrypted')");
//	      db.rawExecSQL("DETACH DATABASE encrypted;");
//
//	      int version=db.getVersion();
//
//	      db.close();
//
//	      db=
//	          SQLiteDatabase.openDatabase(newFile.getAbsolutePath(),
//	        		  "@Override", null,
//	                                      SQLiteDatabase.OPEN_READWRITE);
//	      db.setVersion(version);
//	      db.close();
//
//	      originalFile.delete();
//	      newFile.renameTo(originalFile);
//	    }
//		
//	}

}