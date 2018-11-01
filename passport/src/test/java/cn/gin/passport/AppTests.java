package cn.gin.passport;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class AppTests {


    /**
     * Input:
     * <pre>
     *  1
     *  0
     *  2
     *  0
     *  4
     * </pre>
     *
     * Output:
     * <pre>
     *  12400
     * </pre>
     */
    public static void testExtractZeroToArrayEnd() {

        Scanner in = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        for ( ; ; ) {
            String input = in.nextLine();

            try {
                int e = Integer.valueOf(input);
                if (e < 0) break;
                builder.append(String.valueOf(e));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + e);
                continue;
            }
        }

        char[] chars = builder.toString().toCharArray();

        for (int i = 0; i < chars.length - 1; i++) {

            for (int j = chars.length - 1; j > i; j--) {

                if (chars[j] != '0' && chars[j - 1] == '0') {
                    char temp = chars[j];
                    chars[j] = '0';
                    chars[j - 1] = temp;
                }
            }
        }

        System.out.println("=======================");

        for (char c : chars) {
            System.out.print(c);
        }
    }

    static class A {

        private String name$private = "value$private";
        protected String name$protected = "value$protected";
        public String name$public = "value$public";

        private void readObject(ObjectInputStream in) throws Exception {
            System.out.println("A ------> readObject");
            in.defaultReadObject();
        }

        private void writeObject(ObjectOutputStream out) throws Exception {
            System.out.println("A ------> writeObject");
            out.defaultWriteObject();
        }
    }

    static class SubA extends A implements Serializable {

        private static final long serialVersionUID = 1L;

        private transient String sub$name$private = "sub$value$private";
        protected String sub$name$protected = "sub$value$protected";
        public String sub$name$public = "sub$value$public";

        private void readObject(ObjectInputStream in) throws Exception {
            System.out.println("SubA ------> readObject");
            in.defaultReadObject();
        }

        private void writeObject(ObjectOutputStream out) throws Exception {
            System.out.println("SubA ------> writeObject");
            out.defaultWriteObject();
        }

        @Override
        public String toString() {
            return String.format("[sub$name$private=%s, sub$name$protected=%s, "
                    + "sub$name$public=%s]", this.sub$name$private, this.sub$name$protected, this.sub$name$public);
        }
    }

    public static void main(String[] args) throws Exception {

//        SubA s = new SubA();
//        s.sub$name$public = "123";
//        System.out.println(s);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ObjectOutputStream oos = new ObjectOutputStream(baos);
//        oos.writeObject(s);
//        byte[] bytes = baos.toByteArray();
//
//        System.out.println("============================");
//
//        ByteArrayInputStream bain = new ByteArrayInputStream(bytes);
//        ObjectInputStream ois = new ObjectInputStream(bain);
//        SubA seril = (SubA) ois.readObject();
//        System.out.println(seril);

        testExtractZeroToArrayEnd();
    }
}
