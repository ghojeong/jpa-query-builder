package persistence.sql.dml;

import persistence.sql.view.ColumnFields;
import persistence.sql.view.ColumnNames;
import persistence.sql.view.ColumnValues;
import persistence.sql.view.TableName;

import java.lang.reflect.Field;
import java.util.List;

public class InsertQuery<T> {
    private final T object;

    public InsertQuery(T object) {this.object = object;}

    public String build() {
        final Class<T> clazz = (Class<T>) object.getClass();
        final List<Field> columnFields = ColumnFields.forInsert(clazz);
        return new StringBuilder()
                .append("INSERT INTO ")
                .append(TableName.render(clazz))
                .append(" (")
                .append(ColumnNames.render(columnFields))
                .append(")")
                .append(ColumnValues.render(object, columnFields))
                .toString();
    }
}