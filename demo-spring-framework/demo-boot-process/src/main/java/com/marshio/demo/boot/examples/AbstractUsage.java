package com.marshio.demo.boot.examples;

/**
 * @author marshio
 * @desc ...
 * @create 2024/2/23 15:47
 */
public class AbstractUsage {

    // 在Java中，当一个子类继承父类时，创建子类对象时一定会执行父类的构造函数。
    // 这是因为双亲委派机制，子类对象的构造方法在调用父类构造方法之前，会先调用父类的构造方法。
    public static void main(String[] args) {
        ExtendsClass01 concreteClass = new ExtendsClass01();
        // concreteClass.refresh();

        // 分割线
        System.out.println("分割线");

        // ExtendsClass02 concreteClass2 = new ExtendsClass02();
        // concreteClass2.abstractMethod();

        // System.out.println("分割线");
        //
        // NonAbstractClassSon nonAbstractClassSon = new NonAbstractClassSon();
    }

    public abstract static class AbstractClass {

        public AbstractClass() {
            // 声明子类实例的时候，会调用父类的构造方法
            System.out.println("AbstractClass Construct Method");
            // 调用的方法为被声明的对象的方法，比如声明的类为ExtendsClass01，那么调用的就是ExtendsClass01的abstractMethod方法，前提是子类实现了该方法。
            // 声明的类为ExtendsClass02，那么调用的就是ExtendsClass02的abstractMethod方法。
            abstractMethod();
        }

        public void refresh() {
            // call protected method
            abstractMethod();
        }

        protected void abstractMethod() {
            System.out.println("abstractMethod call");
        }
    }

    public static class ExtendsClass01 extends AbstractClass {

        public ExtendsClass01() {
            System.out.println("ExtendsClass01 Construct Method");
        }

        @Override
        public void refresh() {
            super.refresh();
        }

        @Override
        protected void abstractMethod() {
            // do nothing
            System.out.println("ExtendsClass01 call");
        }
    }

    public static class ExtendsClass02 extends ExtendsClass01 {

        public ExtendsClass02() {
            System.out.println("ExtendsClass02 Construct Method");
        }

        @Override
        protected void abstractMethod() {
            // do nothing
            System.out.println("ExtendsClass02");
        }

    }

    public static class NonAbstractClass {

        public NonAbstractClass() {
            // 声明子类实例的时候，会调用父类的构造方法
            System.out.println("NonAbstractClass Construct Method");
        }
    }

    public static class NonAbstractClassSon extends NonAbstractClass {

        public NonAbstractClassSon() {
            System.out.println("NonAbstractClassSon Construct Method");
        }
    }
}
