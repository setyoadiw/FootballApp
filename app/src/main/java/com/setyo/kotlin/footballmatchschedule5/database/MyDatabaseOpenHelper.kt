package com.setyo.kotlin.footballmatchschedule5.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoritesEvent.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITES, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.EVENT_DATE to TEXT,
                Favorite.EVENT_TIME to TEXT,
                Favorite.ID_HOME to TEXT,
                Favorite.TEAM_HOME to TEXT,
                Favorite.SCORE_HOME to TEXT,
                Favorite.ID_AWAY to TEXT,
                Favorite.TEAM_AWAY to TEXT,
                Favorite.SCORE_AWAY to TEXT,
                Favorite.GK_HOME to TEXT,
                Favorite.GK_AWAY to TEXT,
                Favorite.DF_HOME to TEXT,
                Favorite.DF_AWAY to TEXT,
                Favorite.MF_HOME to TEXT,
                Favorite.MF_AWAY to TEXT,
                Favorite.FW_HOME to TEXT,
                Favorite.FW_AWAY to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITES_TEAM, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_YEAR to TEXT,
                FavoriteTeam.TEAM_STADIUM to TEXT,
                FavoriteTeam.TEAM_OVERVIEW to TEXT,
                FavoriteTeam.TEAM_HEADER to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITES, true)
        db.dropTable(FavoriteTeam.TABLE_FAVORITES_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)