package projeto.Criptografia;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SenhaUtil {

    public static String criptografar(String senha) {


        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = md.digest(senha.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte b : hashBytes){
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Erro ao criptografar senha");
        }

    }

   public static boolean comparar(String senhaDigitada, String hashSalvo){

        String senhaCriptografada = criptografar(senhaDigitada);

        return senhaCriptografada.equals(hashSalvo);
   }

}
