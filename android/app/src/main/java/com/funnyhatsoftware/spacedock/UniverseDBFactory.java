package com.funnyhatsoftware.spacedock;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Thomas on 1/1/2015.
 */
public class UniverseDBFactory extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "spacedock.db";
    private static final int DATABASE_VERSION = 1;

    public UniverseDBFactory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


}
