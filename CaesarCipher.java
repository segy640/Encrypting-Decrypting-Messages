package encryptdecrypt;

interface CaesarCipher {
    abstract char[] cipher(char[] message, int offset);
}
