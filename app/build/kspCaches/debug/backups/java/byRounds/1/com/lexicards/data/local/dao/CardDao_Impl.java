package com.lexicards.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.lexicards.model.Card;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CardDao_Impl implements CardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Card> __insertionAdapterOfCard;

  private final EntityDeletionOrUpdateAdapter<Card> __deletionAdapterOfCard;

  private final EntityDeletionOrUpdateAdapter<Card> __updateAdapterOfCard;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllInDeck;

  public CardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCard = new EntityInsertionAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cards` (`id`,`deckId`,`foreignWord`,`georgianWord`,`foreignLanguage`,`easeFactor`,`interval`,`repetitions`,`nextReviewTime`,`createdAt`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getDeckId());
        statement.bindString(3, entity.getForeignWord());
        statement.bindString(4, entity.getGeorgianWord());
        statement.bindString(5, entity.getForeignLanguage());
        statement.bindDouble(6, entity.getEaseFactor());
        statement.bindLong(7, entity.getInterval());
        statement.bindLong(8, entity.getRepetitions());
        statement.bindLong(9, entity.getNextReviewTime());
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `cards` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfCard = new EntityDeletionOrUpdateAdapter<Card>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `cards` SET `id` = ?,`deckId` = ?,`foreignWord` = ?,`georgianWord` = ?,`foreignLanguage` = ?,`easeFactor` = ?,`interval` = ?,`repetitions` = ?,`nextReviewTime` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Card entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getDeckId());
        statement.bindString(3, entity.getForeignWord());
        statement.bindString(4, entity.getGeorgianWord());
        statement.bindString(5, entity.getForeignLanguage());
        statement.bindDouble(6, entity.getEaseFactor());
        statement.bindLong(7, entity.getInterval());
        statement.bindLong(8, entity.getRepetitions());
        statement.bindLong(9, entity.getNextReviewTime());
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindString(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllInDeck = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cards WHERE deckId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Card card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCard.insert(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<Card> cards, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCard.insert(cards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Card card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Card card, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCard.handle(card);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllInDeck(final String deckId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllInDeck.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, deckId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllInDeck.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Card>> getAllCards() {
    final String _sql = "SELECT * FROM cards ORDER BY nextReviewTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfForeignWord = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignWord");
          final int _cursorIndexOfGeorgianWord = CursorUtil.getColumnIndexOrThrow(_cursor, "georgianWord");
          final int _cursorIndexOfForeignLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignLanguage");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpDeckId;
            _tmpDeckId = _cursor.getString(_cursorIndexOfDeckId);
            final String _tmpForeignWord;
            _tmpForeignWord = _cursor.getString(_cursorIndexOfForeignWord);
            final String _tmpGeorgianWord;
            _tmpGeorgianWord = _cursor.getString(_cursorIndexOfGeorgianWord);
            final String _tmpForeignLanguage;
            _tmpForeignLanguage = _cursor.getString(_cursorIndexOfForeignLanguage);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Card(_tmpId,_tmpDeckId,_tmpForeignWord,_tmpGeorgianWord,_tmpForeignLanguage,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewTime,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getCardsByDeck(final String deckId) {
    final String _sql = "SELECT * FROM cards WHERE deckId = ? ORDER BY nextReviewTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, deckId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfForeignWord = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignWord");
          final int _cursorIndexOfGeorgianWord = CursorUtil.getColumnIndexOrThrow(_cursor, "georgianWord");
          final int _cursorIndexOfForeignLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignLanguage");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpDeckId;
            _tmpDeckId = _cursor.getString(_cursorIndexOfDeckId);
            final String _tmpForeignWord;
            _tmpForeignWord = _cursor.getString(_cursorIndexOfForeignWord);
            final String _tmpGeorgianWord;
            _tmpGeorgianWord = _cursor.getString(_cursorIndexOfGeorgianWord);
            final String _tmpForeignLanguage;
            _tmpForeignLanguage = _cursor.getString(_cursorIndexOfForeignLanguage);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Card(_tmpId,_tmpDeckId,_tmpForeignWord,_tmpGeorgianWord,_tmpForeignLanguage,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewTime,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Card>> getDueCards(final String deckId, final long now) {
    final String _sql = "SELECT * FROM cards WHERE deckId = ? AND nextReviewTime <= ? ORDER BY nextReviewTime ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, deckId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, now);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cards"}, new Callable<List<Card>>() {
      @Override
      @NonNull
      public List<Card> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfForeignWord = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignWord");
          final int _cursorIndexOfGeorgianWord = CursorUtil.getColumnIndexOrThrow(_cursor, "georgianWord");
          final int _cursorIndexOfForeignLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignLanguage");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Card> _result = new ArrayList<Card>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Card _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpDeckId;
            _tmpDeckId = _cursor.getString(_cursorIndexOfDeckId);
            final String _tmpForeignWord;
            _tmpForeignWord = _cursor.getString(_cursorIndexOfForeignWord);
            final String _tmpGeorgianWord;
            _tmpGeorgianWord = _cursor.getString(_cursorIndexOfGeorgianWord);
            final String _tmpForeignLanguage;
            _tmpForeignLanguage = _cursor.getString(_cursorIndexOfForeignLanguage);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Card(_tmpId,_tmpDeckId,_tmpForeignWord,_tmpGeorgianWord,_tmpForeignLanguage,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewTime,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCardCount(final String deckId, final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM cards WHERE deckId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, deckId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCardById(final String id, final Continuation<? super Card> $completion) {
    final String _sql = "SELECT * FROM cards WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Card>() {
      @Override
      @Nullable
      public Card call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfForeignWord = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignWord");
          final int _cursorIndexOfGeorgianWord = CursorUtil.getColumnIndexOrThrow(_cursor, "georgianWord");
          final int _cursorIndexOfForeignLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "foreignLanguage");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "interval");
          final int _cursorIndexOfRepetitions = CursorUtil.getColumnIndexOrThrow(_cursor, "repetitions");
          final int _cursorIndexOfNextReviewTime = CursorUtil.getColumnIndexOrThrow(_cursor, "nextReviewTime");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Card _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpDeckId;
            _tmpDeckId = _cursor.getString(_cursorIndexOfDeckId);
            final String _tmpForeignWord;
            _tmpForeignWord = _cursor.getString(_cursorIndexOfForeignWord);
            final String _tmpGeorgianWord;
            _tmpGeorgianWord = _cursor.getString(_cursorIndexOfGeorgianWord);
            final String _tmpForeignLanguage;
            _tmpForeignLanguage = _cursor.getString(_cursorIndexOfForeignLanguage);
            final double _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getDouble(_cursorIndexOfEaseFactor);
            final int _tmpInterval;
            _tmpInterval = _cursor.getInt(_cursorIndexOfInterval);
            final int _tmpRepetitions;
            _tmpRepetitions = _cursor.getInt(_cursorIndexOfRepetitions);
            final long _tmpNextReviewTime;
            _tmpNextReviewTime = _cursor.getLong(_cursorIndexOfNextReviewTime);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Card(_tmpId,_tmpDeckId,_tmpForeignWord,_tmpGeorgianWord,_tmpForeignLanguage,_tmpEaseFactor,_tmpInterval,_tmpRepetitions,_tmpNextReviewTime,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
