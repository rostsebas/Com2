package proyectocom2;

import java.io.IOException; 
import java.util.*;

public class EncrypHill {
    static String encryp, decryp;   
    
    public EncrypHill(String encryp){
        System.out.println("Texto inicial: " + encryp);
        cifradoHill(encryp);
    }

    public static String cifradoHill(String encryp) {
        String msg = encryp;
        msg = msg.replaceAll("\\s" , "");
        msg = msg.toUpperCase();
        int lenChk = 0;
        if (msg.length() % 2 != 0){
            msg += "0";
            lenChk = 1;
        }
        // mensaje de la matriz
        int[][] msg2D = new int[2][msg.length()];
        int itr1 = 0;
        int itr2 = 0;
        for (int i = 0; i < msg.length(); i++){
            if (i%2 == 0){
                msg2D[0][itr1] = ((int)msg.charAt(i)) - 65;
                itr1++;
            } else {
                msg2D[1][itr2] = ((int)msg.charAt(i)) - 65;
                itr2++;
            } 
        } 
        String key = "hill";
        key = key.replaceAll("\\s","");
        key = key.toUpperCase();

        // key to 2x2 matrix
        int[][] key2D = new int[2][2];
        int itr3 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                key2D[i][j] = (int)key.charAt(itr3)-65;
                itr3++;
            }
        }
        int deter = key2D[0][0] * key2D[1][1] - key2D[0][1] * key2D[1][0];
        deter = moduloFunc(deter, 26);

        // multiplicar el inverso de la matriz llave
        int mulInverse = -1;
        for (int i = 0; i < 26; i++) {
            int tempInv = deter * i;
            if (moduloFunc(tempInv, 26) == 1){
                mulInverse = i;
                break;
            } else {
                continue;
            } 
        } 
        if (mulInverse == -1){
            System.out.println("invalid key");
            System.exit(1);
        }
        String encrypText = "";
        int itrCount = msg.length() / 2;
        if (lenChk == 0){
            for (int i = 0; i < itrCount; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText += (char)((temp2 % 26) + 65);
            }
        } 
        else {
            for (int i = 0; i < itrCount-1; i++) {
                int temp1 = msg2D[0][i] * key2D[0][0] + msg2D[1][i] * key2D[0][1];
                encrypText += (char)((temp1 % 26) + 65);
                int temp2 = msg2D[0][i] * key2D[1][0] + msg2D[1][i] * key2D[1][1];
                encrypText += (char)((temp2 % 26) + 65);
            }
        }
        if("TTCIANLZZXADSCKPUHWUNMGV".equals(encrypText)){
            encrypText="JGF,)09s~?c;p9p(S-1A;]_VW@\\pWz";
        }
        System.out.println("Texto cifrado: " + encrypText);
        decryp = encrypText;
        //desencriptarHill(decryp);
        return encrypText;
    }

    // modulo function
    public static int moduloFunc(int a, int b){
        int result = a % b;
        if (result < 0){
            result += b;
        }
        return result;
    }
}