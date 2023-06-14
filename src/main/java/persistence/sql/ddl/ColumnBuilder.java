package persistence.sql.ddl;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.lang.reflect.Field;

import static persistence.sql.ddl.StringConstant.BLANK;

public class ColumnBuilder {
    private final Field field;

    public ColumnBuilder(Field field) {this.field = field;}

    public String build() {
        return new StringBuilder()
                .append(getColumnName())
                .append(" ")
                .append(getSqlType())
                .append(getNullable())
                .append(getGenerationStrategy())
                .append(getPKSql())
                .toString();
    }

    private String getColumnName() {
        final Column column = field.getDeclaredAnnotation(Column.class);
        final String name = column == null || column.name().isBlank()
                ? field.getName()
                : column.name();
        return name.toLowerCase();
    }

    private String getSqlType() {
        return new StringBuilder()
                .append(TypeMapper.toSqlType(field.getType()))
                .append(getColumnLength())
                .toString();
    }

    private String getColumnLength() {
        final Column column = field.getDeclaredAnnotation(Column.class);
        if (column == null || !field.getType().equals(String.class)) {
            return BLANK;
        }
        return new StringBuilder()
                .append("(")
                .append(column.length())
                .append(")")
                .toString();
    }

    private String getNullable() {
        final Column column = field.getDeclaredAnnotation(Column.class);
        return column == null || column.nullable()
                ? BLANK
                : " NOT NULL";
    }

    private String getPKSql() {
        final String PK_SQL = " PRIMARY KEY";
        return field.isAnnotationPresent(Id.class)
                ? PK_SQL
                : BLANK;
    }

    private String getGenerationStrategy() {
        final GeneratedValue generatedValue = field.getAnnotation(GeneratedValue.class);
        return generatedValue != null && generatedValue.strategy() == GenerationType.IDENTITY
                ? " AUTO_INCREMENT"
                : BLANK;
    }
}
