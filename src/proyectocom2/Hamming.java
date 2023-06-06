package proyectocom2;
import java.io.IOException;
import java.util.*;

public class Hamming {
    static String texto ,bin, result;
    static int m, n, o,p;
    int inf = 0, sup, exp=0;
    static double binario=0; 
    /*public static void main(String[] args) throws IOException {
        Hamming text;
        text = new Hamming("Cristian Sebastian Martínez Arévalo");
    }*/  
    
    public Hamming(String texto) {
        //texto = "Cristian Sebastian Martínez Arévalo";//entrada del dato
        //Pasar de String a vector int.       
        m = texto.length();
        StringBuilder binario = new StringBuilder(); 
        for(int i=0; i < m; i++){
            String tex=Integer.toBinaryString(texto.charAt(i));
            o = tex.length();
            for(int j=0; j < o; j++){
                binario.append((char) (tex.charAt(j)));
                p = binario.length();         
            }        
        }     
        bin = binario.toString();
        char[] cadena = bin.toCharArray();
        int[] Enteros = new int[cadena.length];
        for (int i = cadena.length-1; i >= 0; i--) {
            Enteros[cadena.length-1-i] = Character.getNumericValue(cadena[i]);//Convierte el char en int
        }   
        
        int a[] = Enteros;
        n = a.length;    
	System.out.println("La entrada es:");
	for(int i=0 ; i < n ; i++) {
            System.out.print(a[n-i-1]);
	}
	System.out.println();
	int b[] = generarCodigo(a); //dato con bits de paridad
	System.out.println("El codigo con bits de paridad es:");
	for(int i=0 ; i < b.length ; i++) {
            System.out.print(b[b.length-i-1]);
	}
	System.out.println();	
	
        Random r = new Random();
        sup=n;
        int ram = r.nextInt(sup-inf) + inf;
	int error = ram;
	if(error != 0) {
            b[error-1] = (b[error-1]+1)%2;
	}
	System.out.println("El codigo enviado es:");
	for(int i=0 ; i < b.length ; i++) {
            System.out.print(b[b.length-i-1]);
        }
	System.out.println();
	recibir(b, b.length - a.length);
        
        if(Enteros==a){
            byte[] bytes = texto.getBytes();     
            result = new String(bytes);  
            System.out.println("El texto enviado es: "+result);
        }            
    }
    
    static int[] generarCodigo(int a[]) {
	int b[];	
	// se encuentra el número de bits de paridad requeridos:
	int i=0, count_paridad=0 ,j=0, k=0;
	while(i < a.length) {
            // 2^(bits_de_paridad) = posicion_actual = #bits_mensaje+#bits_paridad+1
            if(Math.pow(2,count_paridad) == i+count_paridad + 1) {
		count_paridad++;
		}
            else {
		i++;
		}
	}
	// longitud de b es igual a la longitud de a mas los bits de paidad
	b = new int[a.length + count_paridad];
	for(i=1 ; i <= b.length ; i++) {
            if(Math.pow(2, j) == i) {
            // se encuentra la posicion de los bits de paridad.
		b[i-1] = 2;
		j++;
            }
            else {
                b[k+j] = a[k++];
            }
	}
	for(i=0 ; i < count_paridad ; i++) {
            // Se colocan los bits de paridad en su respectiva posicion:
            b[((int) Math.pow(2, i))-1] = obtenerParidad(b, i);
	}
	return b;
    }
    
    static int obtenerParidad(int b[], int power) {
	int paritdad = 0;
	for(int i=0 ; i < b.length ; i++) {
            if(b[i] != 2) {
                // Si i no contiene ningun valor, se almacena en k y se incrementa +1,
                int k = i+1;
                // Se convierte en binario:
                String s = Integer.toBinaryString(k);
                //Si el valor de x es 1, entonces se almacena el valor en la posicion dada
                //luego se evalua si es 1 o 0 para ver el valor de la paridad
                int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
                if(x == 1) {
                    if(b[i] == 1) {
			paritdad = (paritdad+1)%2;
                    }
                }
            }
	}
	return paritdad;
    }
    
    static void recibir(int a[], int count_paridad) {
	// Esta parte se realiza codigo hamming en el vector a, se agregan los bits de paridad
	// y se detecta los errores.
	int power;
	int paridad[] = new int[count_paridad]; // 'paridad' almacena los valores para comparar paridad.
	String sindrome = new String();	// sindrome se utiliza para almacenar la locación del error.
	for(power=0 ; power < count_paridad ; power++) {
	// Se revisa la paridad.
            for(int i=0 ; i < a.length ; i++) {
            // extrae el bit de 2^(power):
                int k = i+1;
		String s = Integer.toBinaryString(k);
		int bit = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
		if(bit == 1) {
                    if(a[i] == 1) {
			paridad[power] = (paridad[power]+1)%2;
                    }
		}
            }
            sindrome = paridad[power] + sindrome;
	}
	// Se comparan los bits de paridad para ver si hay un error en el elnvio
	int error_location = Integer.parseInt(sindrome, 2);
	if(error_location != 0) {
            System.out.println("La locacion del error esta en la posicion " + error_location + ".");
            a[error_location-1] = (a[error_location-1]+1)%2;
            System.out.println("El codigo correcto es:");
            for(int i=0 ; i < a.length ; i++) {
		System.out.print(a[a.length-i-1]);
            }
            System.out.println();
	}
	else {
            System.out.println("No hay error en el codigo.");
	}
	// Se extrae el codigo original con la correxion del error
	System.out.println("El codigo original es:");
	power = count_paridad-1;
	for(int i=a.length ; i > 0 ; i--) {
            if(Math.pow(2, power) != i) {
		System.out.print(a[i-1]);
                
            }
            else {
		power--;
            }
	}
        System.out.println();
    }
    
}
