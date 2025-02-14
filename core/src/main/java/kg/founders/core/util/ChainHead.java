//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kg.founders.core.util;

public class ChainHead implements ChainValidator {
    ChainElement first;

    public ChainHead() {
    }

    public ChainElement then(Validatable then) {
        return this.first = new ChainElement(this, then);
    }
}
