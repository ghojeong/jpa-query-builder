package persistence.sql.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnValueTest {

    @Test
    @DisplayName("String 이면 따옴표가 붙는다.")
    void stringType() {
        Assertions.assertThat(
                ColumnValue.build("Nickname")
        ).isEqualTo("'Nickname'");
    }

    @Test
    @DisplayName("String 이 아니면 따옴표가 붙지 않는다.")
    void integerType() {
        Assertions.assertThat(
                ColumnValue.build(1)
        ).isEqualTo("1");
    }
}
