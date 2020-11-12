package encryptdecrypt;

class ShiftCipher implements CaesarCipher{

    @Override
    public char[] cipher(char[] message, int offset) {
        char[] result = new char[message.length];
        for (int i =0; i < message.length; i++) {
            if (Character.toLowerCase(message[i]) >= 'a' && message[i] <= 'z') {
                if (offset > 0) {
                    result[i] = (char) (Character.toLowerCase(message[i]) + offset > 'z' ?
                            message[i] + offset - 26 : message[i] + offset);
                } else if (offset < 0) {
                    result[i] = (char) (Character.toLowerCase(message[i]) + offset < 'a' ?
                            message[i] + offset + 26 : message[i] + offset);
                }

            } else {
                result[i] = message[i];
            }
        }
        return result;
    }
}
