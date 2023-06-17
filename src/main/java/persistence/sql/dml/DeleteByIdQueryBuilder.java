package persistence.sql.dml;

import persistence.sql.common.TableName;

public class DeleteByIdQueryBuilder<T> {
    private final Class<T> clazz;

    public DeleteByIdQueryBuilder(Class<T> clazz) {this.clazz = clazz;}

    public String build(Object id) {
        return new StringBuilder()
                .append("DELETE FROM ")
                .append(new TableName<>(clazz))
                .append(new WhereIdQueryBuilder<>(clazz).build(id))
                .toString();
    }
}