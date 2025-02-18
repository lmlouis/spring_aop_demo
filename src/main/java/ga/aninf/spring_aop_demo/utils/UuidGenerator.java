package ga.aninf.spring_aop_demo.utils;

import java.util.Random;
import java.util.UUID;

public class UuidGenerator {
    /**
     * Génère un UUID aléatoirement
     * @return UUID
     */
    public static UUID generateUuid() {
        StringBuilder uuid = new StringBuilder();
        String chars = "0123456789abcdef";
        Random random = new Random();

        for (int i = 0; i < 36; i++) {
            if (i == 8 || i == 13 || i == 18 || i == 23) {
                uuid.append('-');
            } else if (i == 14) {
                uuid.append('4');
            } else if (i == 19) {
                int r = random.nextInt(16);
                uuid.append(chars.charAt(r & 0x3 | 0x8)); // Garantit que le premier bit est 1 et le second est aléatoire
            } else {
                int r = random.nextInt(16);
                uuid.append(chars.charAt(r));
            }
        }
        
        return  UUID.fromString(uuid.toString());
    }
}
