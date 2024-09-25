package oop;

public class BaseClassTest {

    public static void main(String[] args) {
        BaseClass c = new BaseClass2();
        System.out.print(c.getLocalCode(null, false));
    }
}

abstract class BaseClass {
    protected abstract Number getLocalCode(String value, boolean IsValidated);
}

abstract class BaseClass1 extends BaseClass {
    protected abstract Number getLocalCode(String value, boolean IsValidated);
}

class BaseClass2 extends BaseClass {
    public Number getLocalCode(String value, boolean IsValidated) {
        return Double.valueOf(2.5);
    }
}

class BaseClass3 extends BaseClass {
    public Integer getLocalCode(String value, boolean IsValidated) {
        return 1;
    }
}