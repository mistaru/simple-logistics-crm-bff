//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kg.founders.core.util;

@FunctionalInterface
public interface ConsumerE<T, E extends Exception> {
    void accept(T t) throws E;
}
