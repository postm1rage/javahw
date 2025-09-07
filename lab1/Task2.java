public class Task2 {
    public static void main(String[] args) {
        String str = "make install";
        StringBuilder sb = new StringBuilder(str);
        String reversed = sb.reverse().toString();
        System.out.println("Original: " + str);
        System.out.println("Reversed (StringBuilder): " + reversed);
        System.out.print("Reversed (charAt): ");
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
    }
}
