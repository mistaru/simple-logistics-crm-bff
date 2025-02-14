//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package kg.founders.core.util;

public class ChainElement implements ChainValidator, Validatable {
    private final Validatable then;
    private ChainHead head;
    private ChainElement child;

    private static String validate(ChainElement validator) {
        String error = validator.then.validateMessage();
        return error == null && validator.child != null ? validate(validator.child) : error;
    }

    ChainElement(ChainHead head, Validatable then) {
        this.head = head;
        this.then = then;
    }

    public ChainElement then(Validatable then) {
        ChainElement validator = new ChainElement(this.head, then);
        this.head = null;
        this.child = validator;
        return validator;
    }

    public String validateMessage() {
        return validate(this.head.first);
    }
}
