package proyectocom2;

import java.io.IOException; 
import java.util.*;
import static proyectocom2.EncrypCesar.NewTex;
import static proyectocom2.EncrypHill.decryp;
import static proyectocom2.Hamming.result;

public class ProyectoCOM2 {
    public static void main(String[] args) throws IOException {
        Scanner i = new Scanner(System.in);
        System.out.println("Ingrese el tipo de codificación."); 
        System.out.print("(1)Cesar, (2)Hill: ");
        int codigo = i.nextInt();
        
        switch(codigo){
            case 1:
                EncrypCesar tex1;
                tex1 = new EncrypCesar("Cristian Sebastian Martinez Arevalo");
                Hamming texta;
                texta = new Hamming(NewTex);
                DecrypCesar tex2;
                tex2 = new DecrypCesar(result);
                break;           
            case 2:
                EncrypHill tex3;
                tex3 = new EncrypHill("Jose Antonio Ramirez Cordova");
                Hamming textb;
                textb = new Hamming(decryp);
                DecrypHill tex4;
                tex4 = new DecrypHill(result);
                break;
            default:
                System.out.println("Error");
        } 
    }  
}
