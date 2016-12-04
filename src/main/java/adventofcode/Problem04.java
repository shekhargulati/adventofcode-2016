package adventofcode;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;
import static strman.Strman.between;

public class Problem04 {

    public static int sumOfSectorIds(List<String> roomNames) {
        return roomNames.stream()
                .peek(System.out::println)
                .filter(name -> isRealRoom(name))
                .mapToInt(Problem04::sectorId)
                .sum();
    }

    private static int sectorId(String name) {
        String[] parts = name.split("-");
        String lastPart = parts[parts.length - 1];
        return Integer.parseInt(lastPart.substring(0, lastPart.indexOf("[")));
    }

    public static boolean isRealRoom(final String name) {
        /*
        Split string by -
        filter out last part as it is checksum
        Put all the strings in a stream
        find checksum from name
            group chars by their frequency
        if found checksum equals checksum in the name then return true else false
         */

        String[] parts = name.split("-");
        String checksum = between(parts[parts.length - 1], "[", "]")[0];
        Map<String, Long> charWithFrequency = Arrays.stream(Arrays.copyOfRange(parts, 0, parts.length - 1))
                .flatMap(s -> Arrays.stream(s.split("")))
                .collect(groupingBy(
                        identity(),
                        counting()));
//        System.out.println(charWithFrequency);
        Comparator<Map.Entry<String, Long>> entryComparator = (e1, e2) -> {
            if (Objects.equals(e1.getValue(), e2.getValue())) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return Long.valueOf(e2.getValue() - e1.getValue()).intValue();
        };

        String calculatedChecksum = charWithFrequency
                .entrySet()
                .stream()
                .sorted(entryComparator)
                .limit(5)
                .map(Map.Entry::getKey).collect(joining());
//        System.out.println(calculatedChecksum);
        return Objects.equals(checksum, calculatedChecksum);
    }

    public static int sectorIdOf(List<String> roomNames, String decrypted) {
        AbstractMap.SimpleEntry<String, Integer> entry = roomNames.stream()
                .filter(Problem04::isRealRoom)
                .map(name -> new AbstractMap.SimpleEntry<>(toRealName(name), sectorId(name)))
                .peek(System.out::println)
                .filter(realName -> Objects.equals(realName.getKey(), decrypted))
                .findFirst().get();
        return entry.getValue();
    }

    public static String toRealName(final String roomName) {
        String name = roomName.substring(0, roomName.lastIndexOf("-"));
        int shiftKey = sectorId(roomName);
        return Arrays.stream(name.split("")).map(s -> {
            if(Objects.equals(s, "-")){
                return " ";
            }else{
                int charPosistion = ALPHABET.indexOf(s);
                int keyVal = (charPosistion + shiftKey) % 26;
                if (keyVal < 0)
                {
                    keyVal = ALPHABET.length() + keyVal;
                }
                char replaceVal = ALPHABET.charAt(keyVal);
                return String.valueOf(replaceVal);
            }
        }).collect(joining());
    }

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String decrypt(String cipherText, int shiftKey)
    {
        cipherText = cipherText.toLowerCase();
        String plainText = "";
        for (int i = 0; i < cipherText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(cipherText.charAt(i));
            int keyVal = (charPosition + shiftKey) % 26;
            if (keyVal < 0)
            {
                keyVal = ALPHABET.length() + keyVal;
            }
            char replaceVal = ALPHABET.charAt(keyVal);
            plainText += replaceVal;
        }
        return plainText;
    }
}
