//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kg.founders.core.util;

import com.google.common.base.Strings;
import kg.founders.core.model.audit.IdBased;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Supplier;

public interface ChainValidator {
    ChainElement then(Validatable validatable);

    default ChainElement then(Long id) {
        return this.then(() -> {
            return id != null && id < 1L ? "Не верный диапазон id" : null;
        });
    }

    default ChainElement then(Supplier<IdBased> supplier, String message) {
        return this.then(() -> {
            IdBased idBased = (IdBased)supplier.get();
            if (idBased == null) {
                return (String)Objects.requireNonNull(message);
            } else {
                Long id = idBased.getId();
                return id != null && id < 1L ? "Не верный диапазон id" : null;
            }
        });
    }

    default ChainElement thenNotEmpty(Supplier<String> supplier, String message) {
        return this.then(() -> {
            return Strings.isNullOrEmpty((String)supplier.get()) ? message : null;
        });
    }

    default ChainElement thenNotNull(Supplier<?> supplier, String message) {
        return this.then(() -> {
            return supplier.get() == null ? message : null;
        });
    }

    default ChainElement thenMatch(Supplier<String> supplier, String regex, String message) {
        return this.then(() -> {
            return ((String)supplier.get()).matches(regex) ? null : message;
        });
    }

    default ChainElement thenMatchOrNull(Supplier<String> supplier, String regex, String message) {
        return this.then(() -> {
            return Strings.isNullOrEmpty((String)supplier.get()) ? null : (((String)supplier.get()).matches(regex) ? null : message);
        });
    }

    default ChainElement thenIsNonNegativeBigDecimal(BigDecimal value, String message) {
        return this.then(() -> {
            return value != null && value.signum() >= 0 ? null : message;
        });
    }

    default ChainElement thenIsPositiveBigDecimal(BigDecimal value, String message) {
        return this.then(() -> {
            return value != null && value.signum() > 0 ? null : message;
        });
    }

    default <U extends Comparable<U>> ChainElement thenInRange(U value, U min, U max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalStateException("минимум не может быть больше чем максимум");
        } else {
            return this.then(() -> {
                return value.compareTo(min) >= 0 && value.compareTo(max) <= 0 ? null : "Значение должно быть в диапазоне от " + min + " до " + max;
            });
        }
    }

    default <U extends Comparable<U>> ChainElement thenInRangeOrNull(U value, U min, U max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalStateException("минимум не может быть больше чем максимум");
        } else {
            return this.then(() -> {
                return value == null ? null : (value.compareTo(min) >= 0 && value.compareTo(max) <= 0 ? null : "Значение должно быть в диапазоне от " + min + " до " + max);
            });
        }
    }

    default ChainElement thenIsNonNegativeInteger(Integer value, String message) {
        return this.then(() -> {
            return value != null && value >= 0 ? null : message;
        });
    }

    default ChainElement thenIsPositiveInteger(Integer value, String message) {
        return this.then(() -> {
            return value != null && value > 0 ? null : message;
        });
    }

    default ChainElement thenIsPositive(BigDecimal value, String message) {
        return this.then(() -> {
            return value != null && value.compareTo(BigDecimal.ZERO) >= 0 ? null : message;
        });
    }

    default ChainElement thenIsPositiveOrNull(Integer value, String message) {
        return this.then(() -> {
            return value != null ? (value < 0 ? message : null) : null;
        });
    }

    default <U extends Comparable<U>> ChainElement thenLess(U first, U second, String message) {
        return this.then(() -> {
            return first.compareTo(second) < 0 ? null : message;
        });
    }

    default <U extends Comparable<U>> ChainElement thenLessOrEquals(U first, U second, String message) {
        return this.then(() -> {
            return first.compareTo(second) <= 0 ? null : message;
        });
    }

    default <U extends Comparable<U>> ChainElement thenMore(U first, U second, String message) {
        return this.then(() -> {
            return first.compareTo(second) > 0 ? null : message;
        });
    }

    default <U extends Comparable<U>> ChainElement thenMoreOrEquals(U first, U second, String message) {
        return this.then(() -> {
            return first.compareTo(second) >= 0 ? null : message;
        });
    }

    default ChainElement thenIsMultipleOfSum(BigDecimal sum, BigDecimal value, String message) {
        return this.then(() -> {
            return value == null ? null : (sum.remainder(value).compareTo(BigDecimal.ZERO) == 0 ? null : message);
        });
    }

    default ChainElement thenPassword(Supplier<String> supplier) {
        return this.then(() -> {
            String p = (String)supplier.get();
            if (p != null && !p.isEmpty() && !p.isBlank()) {
                if (p.length() >= 8 && p.length() <= 20) {
                    if (!p.matches("^[0-9a-zA-Z!@#$%^&*(){}\\[\\]_\\-=+~`/\\\\,.<>]{8,20}$")) {
                        return "Пароль может состоять из цифр, символов латинских нижнего и верхнего регистра и спецсимволов \"!@#$%^&*(){}[]_-=+~`/\\,.<>\"";
                    } else if (!p.matches(".*[0-9]+.*")) {
                        return "Пароль должен содержать хотя бы одну цифру";
                    } else if (!p.matches(".*[a-z]+.*")) {
                        return "Пароль должен содержать хотя бы одну латинскую букву в нижнем регистре";
                    } else {
                        return !p.matches(".*[A-Z]+.*") ? "Пароль должен содержать хотя бы одну латинскую букву в верхнем регистре" : null;
                    }
                } else {
                    return "Пароль должен быть от 8-ми до 20-ти символов";
                }
            } else {
                return "Пароль не может быть пустым";
            }
        });
    }

    static ChainHead create() {
        return new ChainHead();
    }
}
