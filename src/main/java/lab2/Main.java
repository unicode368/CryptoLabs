package lab2;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String[] cipheredText = ("280dc9e47f3352c307f6d894ee8d534313429a79c1d8a6021f8a8eabca919cfb685a0d468973625e757490daa981ea6b\n" +
                "3a0a9cab782b4f8603eac28aadde1151005fd46a859df21d12c38eaa858596bf2548000e883d72117466c5c3a580f66b\n" +
                "3a0adee4783a538403b9c29eaac958550242d3778ed9a61918959bf4ca849afa68450f5edc6e311a7f7ed1d7ec\n" +
                "3a0adee461354e8c1cfcc39bef8d5e40525fdc6bc0dee359578290bcca849afa685a1e5c897362\n" +
                "3a0adab0282b5c9719fcc38caac054541b449a62cf9df21d509690af858286f731091a4890786252\n" +
                "390adeaa283358c318f0c08befc157061f59dd65dd9dee1c04c38fad839586ea3b0903489078\n" +
                "390bcfac283a1d8111ebc8d8e8c2554d1b5e852dfed5e955008c8bb48ed094fe3a4d0b45883d731b7b609c\n" +
                "3a0d9ba37a2e539750f8c39caade464313449a78c7d9e3075782deaf8f9180e66845074f9e31\n" +
                "2c17cfe47c335c9750edc59daac9434313549a62cf9df51a1a868ab0839e95bf294f1a4c893d751b7b66d882\n" +
                "3a0adee47d35598a03fac28eefdf54011610d962dcd3f2070ecfdebe989f9fbf3f41015a9e3d73116f60de\n" +
                "200d9bb07a3a4b861cf5c88aaadf54520742d47e859df6000d9992bd99d086f72d09194097713d\n" +
                "2f0cdfe4653a568603b9d88baadf50521a55c82dcbd8e707579796b79995d2f624451d098c7831167b64d5\n" +
                "3a0adaaa283d519a50edc2d8e5d9594300439a79c1dcf2550086deb3849f85bf26461a09947b2e\n" +
                "3a0aceb72838528d03fac49de4ce5406165fce6589d0e71e12c39db79d9180fb3b09014fdb68625e7b7edc82\n" +
                "2f0cdfe47c33489050edc59daac350521b46df2dc1c8e3551885deaa8f839df33d5d074695\n" +
                "27119bb76138568f19fcc9d8e58a54545247d379c19df21d12c38eb98695d2fc295a1a09947b310a727dc5c9a898a3\n" +
                "2f0cdfe46d35498602e9df91f9c842061d569a6adbd8e701579397ac82d093f12c09034696787f0a\n" +
                "390bcfac282f558a03b9df9dedcc43425244d268c0cfa61602918cbd848481bf3c5c1c47db7c660c63\n" +
                "2f0cdfe464344e8650edc59daac3504b1710d56b89dce5011e8c90f6\n").split("\n");
        for (int i = 0; i < cipheredText.length - 1; i++) {
            String xoredEncryptedStrings = new String(xorWithKey(cipheredText[i].getBytes(),
                    cipheredText[i + 1].getBytes()), StandardCharsets.UTF_8);
            System.out.println(xoredEncryptedStrings);
        }
    }

    private static byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }
}
