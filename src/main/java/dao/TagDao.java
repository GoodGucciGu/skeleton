package dao;

import api.ReceiptResponse;
import generated.tables.records.*;
import generated.tables.Tags;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void insert(String label, int receipts_id) {
        dsl.insertInto(TAGS, TAGS.RECEIPTS_ID, TAGS.LABEL)
                .values(receipts_id, label)
                .execute();
    }

    public List<Integer> getAllReceiptsFORTags(String tagName) {
        return dsl.selectFrom(TAGS)
                .where(TAGS.LABEL.eq(tagName))
                .fetch(TAGS.RECEIPTS_ID, Integer.class);
    }
}
