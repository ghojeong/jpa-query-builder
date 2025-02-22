package persistence.sql.util;

import domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnNamesTest {
    @Test
    @DisplayName("Person 클래스의 필드들을 ColumnNames 로 변환할 수 있다.")
    void filterTransient() {
        Assertions.assertThat(ColumnNames.build(
                ColumnFields.forQuery(Person.class)
        )).isEqualTo("id, nick_name, old, email");
    }
}
