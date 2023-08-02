public class Aug2 {
    public static void main(String[] args) {
        String name = "Nicky Le";

        int indexOfSubString = name.indexOf("Le"); // 6
        System.out.println(indexOfSubString);

        indexOfSubString = name.indexOf("Brian"); // -1 => substring doesnt exist
        System.out.println(indexOfSubString);
    }
}
