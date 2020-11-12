package encryptdecrypt;

class UnicodeCipher implements CaesarCipher{

    @Override
    public char[] cipher(char[] message, int offset) {
        char[] result = new char[message.length];
        for (int i = 0; i < message.length; i++) {
            result[i] = (char) (message[i] +  offset);
        }
        return result;
    }
}
