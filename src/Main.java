import java.io.*;
import java.util.Scanner;

/**
 * Created by Andrei on 17/11/2015.
 */
public class Main {

    private static final String PATH = "C:\\RAID5\\";

    //Converte o arquivo em bytes
    public static byte[] parseFileToByte(File file){

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
    public static void createDisks(){

        File disk0 = new File(PATH + "Disk0");
        File disk1 = new File(PATH + "Disk1");
        File parity = new File(PATH + "Parity");
        boolean isCreated;

        if (!disk0.exists()){
            isCreated = disk0.mkdirs();
            if (!isCreated) System.out.println("Disk0 não pode ser criado!");
            else System.out.println("Disk0 criado com sucesso!");
        }
        if (!disk1.exists()){
            isCreated = disk1.mkdirs();
            if (!isCreated) System.out.println("Disk1 não pode ser criado!");
            else System.out.println("Disk1 criado com sucesso!");
        }
        if (!parity.exists()){
            isCreated = parity.mkdirs();
            if (!isCreated) System.out.println("Parity não pode ser criado!");
            else System.out.println("Parity criado com sucesso!");
        }
    }

    //Cria o arquivo
    public static void creteFile(){
        try {
            FileWriter file = new FileWriter(PATH + "Hino.txt");
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
            file.close();
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }
    }

    //Grava os arquivos de bytes nos discos
    public static void writeByteFileInDisk(byte[] arrayByte){

    }

    //Apaga um disco para teste de recuperação da paridade
    public static void deleteDisk(){

        Scanner sc = new Scanner(System.in);
        File diskForRemove;

        System.out.println("Escolha o disco:");
        System.out.println("0. Disco 0;");
        System.out.println("1. Disco 1.");

        System.out.printf("Disco: ");
        int option = sc.nextInt();
        switch (option){
            case 0:
                diskForRemove = new File(PATH + "Disk0");
                if (!diskForRemove.delete()) System.out.println("Disco0 não pode ser deletado!");
                else System.out.println("Disco0 foi deletado com sucesso!");
                break;
            case 1:
                diskForRemove = new File(PATH + "Disk1");
                if (!diskForRemove.delete()) System.out.println("Disco1 não pode ser deletado!");
                else System.out.println("Disco1 foi deletado com sucesso!");
                break;
        }

    }

    //Recupera o disco com o disco de paridade

    public static void main(String[] args){
        createDisks();
        creteFile();
    }
}
