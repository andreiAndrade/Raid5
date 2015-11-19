import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrei on 17/11/2015.
 */
public class Main {

    //region Attributes
    private static final String PATH = "C:\\RAID5\\";
    private static File disk0;
    private static File disk1;
    private static File parity;
    private static File file;
    //endregion

    //region PrivateMethods
    //Converte o arquivo em bytes
    private static byte[] parseFileToByte(File file){

        int lengthFile = (int)file.length();
        byte[] bytesArray = new byte[lengthFile];
        FileInputStream fileLikeStream  = null;

        try {

            fileLikeStream = new FileInputStream(file);
            fileLikeStream.read(bytesArray, 0, lengthFile);

        } catch (FileNotFoundException fnfex) {
            System.out.println("Aruivo não encontrado!");
        } catch (IOException ioex) {
            System.out.println("Erro inesperado!");
        }

        return bytesArray;
    }

    //Cria diretórios que representaram os disco 0, 1 e de paridade
    private static void createDisks(){

        disk0 = new File(PATH + "Disk0");
        disk1 = new File(PATH + "Disk1");
        parity = new File(PATH + "Parity");

        if (!disk0.exists()){
            if (!disk0.mkdirs()) System.out.println("Disk0 não pode ser criado!");
            else System.out.println("Disk0 criado com sucesso!");
        }
        if (!disk1.exists()){
            if (!disk1.mkdirs()) System.out.println("Disk1 não pode ser criado!");
            else System.out.println("Disk1 criado com sucesso!");
        }
        if (!parity.exists()){
            if (!parity.mkdirs()) System.out.println("Parity não pode ser criado!");
            else System.out.println("Parity criado com sucesso!");
        }
    }

    //Cria o arquivo
    private static void creteFile(){
        try {
            file = new File(PATH + "Hino.txt");
            PrintWriter w = new PrintWriter(file);

            w.println("Glória do desporto nacional");
            w.println("Oh, Internacional");
            w.println("Que eu vivo a exaltar");
            w.println("Levas a plagas distantes");
            w.println("Feitos relevantes");
            w.println("Vives a brilhar");
            w.println("Correm os anos, surge o amanhã");
            w.println("Radioso de luz, varonil");
            w.println("Segue a tua senda de vitórias");
            w.println("Colorado das glórias");
            w.println("Orgulho do Brasil");
            w.println("");
            w.println("É teu passado alvirubro");
            w.println("Motivo de festas em nossos corações");
            w.println("O teu presente diz tudo");
            w.println("Trazendo à torcida alegres emoções");
            w.println("Colorado de ases celeiro");
            w.println("Teus astros cintilam num céu sempre azul");
            w.println("Vibra o Brasil inteiro");
            w.println("Com o clube do povo do Rio Grande do Sul");
            w.println("");
            w.println("Glória do desporto nacional");
            w.println("Oh, Internacional");
            w.println("Que eu vivo a exaltar");
            w.println("Levas a plagas distantes");
            w.println("Feitos relevantes");
            w.println("Vives a brilhar");
            w.println("Correm os anos, surge o amanhã");
            w.println("Radioso de luz, varonil");
            w.println("Segue a tua senda de vitórias");
            w.println("Colorado das glórias");
            w.println("Orgulho do Brasil");
            w.close();
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }
    }

    //Grava os arquivos de bytes nos discos
    private static void writeFileLikeByteInDisk(byte[] arrayByte){

    }

    //Apaga um disco para teste de recuperação da paridade
    private static void deleteDisk(){

        Scanner sc = new Scanner(System.in);

        System.out.println("--Remover disco--");
        System.out.println("Escolha o disco:");
        System.out.println("0. Disk0;");
        System.out.println("1. Disk1.");

        System.out.printf("Disco: ");
        int option = sc.nextInt();
        switch (option){
            case 0:
                if (disk0.exists()) {
                    if (!disk0.delete()) System.out.println("Disk0 não pode ser deletado!");
                    else System.out.println("Disk0 foi deletado com sucesso!");
                } else{
                    System.out.println("O disco solicitado não foi encontrado");
                }
                break;
            case 1:
                if (disk0.exists()) {
                    if (!disk1.delete()) System.out.println("Disk1 não pode ser deletado!");
                    else System.out.println("Disk1 foi deletado com sucesso!");
                } else {
                    System.out.println("O disco solicitado não foi encontrado");
                }
                break;
        }

    }

    //Recupera o disco com o disco de paridade
    private static void recoveryDisk(){

    }
    //endregion

    public static void main(String[] args) throws FileNotFoundException {

    }
}
