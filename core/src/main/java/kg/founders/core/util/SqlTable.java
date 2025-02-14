package kg.founders.core.util;

import org.intellij.lang.annotations.Language;

@Language(
        value = "sql",
        prefix = "SELECT * FROM "
)
public @interface SqlTable {
}