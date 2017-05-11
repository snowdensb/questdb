package com.questdb.ql.impl.sys;

import com.questdb.factory.ReaderFactory;
import com.questdb.factory.configuration.RecordMetadata;
import com.questdb.ql.CancellationHandler;
import com.questdb.ql.Record;
import com.questdb.ql.RecordCursor;
import com.questdb.ql.StorageFacade;
import com.questdb.ql.ops.AbstractCombinedRecordSource;
import com.questdb.std.DirectInputStream;
import com.questdb.std.ObjList;
import com.questdb.std.str.CharSink;
import com.questdb.std.time.DateLocaleFactory;

import java.io.OutputStream;

public class $LocalesRecordSource extends AbstractCombinedRecordSource {
    private final ObjList<CharSequence> locales;
    private final int count;
    private final RecordMetadata metadata = new $LocalesRecordMetadata();
    private final RecordImpl record;
    private int index = 0;

    public $LocalesRecordSource(DateLocaleFactory dateLocaleFactory) {
        locales = dateLocaleFactory.getAll().keys();
        count = locales.size();
        this.record = new RecordImpl(locales);
    }

    @Override
    public void close() {
        // nothing to do
    }

    @Override
    public RecordMetadata getMetadata() {
        return metadata;
    }

    @Override
    public RecordCursor prepareCursor(ReaderFactory factory, CancellationHandler cancellationHandler) {
        this.index = 0;
        return this;
    }

    @Override
    public Record getRecord() {
        return record;
    }

    @Override
    public Record newRecord() {
        return new RecordImpl(locales);
    }

    @Override
    public StorageFacade getStorageFacade() {
        return null;
    }

    @Override
    public void releaseCursor() {
    }

    @Override
    public void toTop() {
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public Record next() {
        return record.of(index++);
    }

    @Override
    public Record recordAt(long rowId) {
        return record.of((int) rowId);
    }

    @Override
    public void recordAt(Record record, long atRowId) {
        ((RecordImpl) record).of((int) atRowId);
    }

    @Override
    public boolean supportsRowIdAccess() {
        return true;
    }

    @Override
    public void toSink(CharSink sink) {

    }

    private static class RecordImpl implements Record {

        private final ObjList<CharSequence> locales;
        int index;

        public RecordImpl(ObjList<CharSequence> locales) {
            this.locales = locales;
        }

        @Override
        public byte get(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void getBin(int col, OutputStream s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public DirectInputStream getBin(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getBinLen(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean getBool(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getDate(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public double getDouble(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public float getFloat(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public CharSequence getFlyweightStr(int col) {
            return locales.getQuick(index);
        }

        @Override
        public CharSequence getFlyweightStrB(int col) {
            return locales.getQuick(index);
        }

        @Override
        public int getInt(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getLong(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public long getRowId() {
            return index;
        }

        @Override
        public short getShort(int col) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void getStr(int col, CharSink sink) {
            sink.put(locales.getQuick(index));
        }

        @Override
        public int getStrLen(int col) {
            return locales.getQuick(index).length();
        }

        @Override
        public String getSym(int col) {
            throw new UnsupportedOperationException();
        }

        private Record of(int index) {
            this.index = index;
            return this;
        }
    }
}