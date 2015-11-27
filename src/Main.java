import java.io.*;
import java.util.Scanner;

//TO DO
//* write disk0, disk1 and parity on real files (????)
//* check if the file was deleted to then recover it

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

        int fileLength = (int)file.length();
        byte[] bytesArray = new byte[fileLength];
        FileInputStream fileLikeStream  = null;

        try {

            fileLikeStream = new FileInputStream(file);
            fileLikeStream.read(bytesArray, 0, fileLength);

        } catch (FileNotFoundException fnfex) {
            System.out.println("Arquivo não encontrado!");
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

    //cria paridade dos discos
    private static byte[] fillParity(byte[] disk0, byte[] disk1) {
        byte[] parity = new byte[disk0.length];
        
        for(int i = 0; i < parity.length; i++) {
            parity[i] = disk0[i] - disk1[i];

        }

        return parity;
    }
    


    //Cria o arquivo
    private static void createFile(){
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
    private static byte[] recoverDisk(byte[] disk0, byte[] parity) {
        byte[] recovered = new byte[disk0.length];
        
        for(int i = 0; i < parity.length; i++) {
            recovered[i] = disk0[i] + parity[i];

        }

        return recovered;
    }
    //endregion


    public static void main(String[] args) throws FileNotFoundException {

        createFile();

        int fileLength = (int)file.length();
        byte[] bytesArray = new byte[lengthFile];

        bytesArray = parseFileToByte(file);

        int count = 0;

        byte[] disk0Byte;
        byte[] disk1Byte;
        byte[] parityByte;

        //dividing file in two different disks
        for(byte element : bytesArray) {
            if(count < (bytesArray/2)) {
                disk0Byte[count] = element;
            } else {
                disk1Byte[count] = element;
            }
        }

        //call method to fill the parity disk
        parity = fillParity(disk0Byte, disk1Byte);

        //write disks into real files
        try {
            for(byte element : disk0Byte) {
                File disk0file = new File(PATH + disk0 + "disk0_byte" + element + ".txt");
                PrintWriter w = new PrintWriter(disk0file);
                w.println(element);
            }
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }

        try {
            File disk1file = new File(PATH + disk1 + "disk1_byte" + element + ".txt");
            PrintWriter w = new PrintWriter(disk1file);
            for(byte element : disk1Byte) {
                w.println(element);
            }
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }


        try {
            File parityfile = new File(PATH + parity + "parity_byte" + element + ".txt");
            PrintWriter w = new PrintWriter(parityfile);
            for(byte element : parityByte) {
                w.println(element);
            }
            System.out.println("Arquivo criado com sucesso!");
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }
 
    }

    // private static File writeDiskInFile(byte[] disk) {
    //     try {
    //         parity = new File(PATH + "parity.txt");
    //         PrintWriter w = new PrintWriter(parity);
    //         for(byte element : parityByte) {
    //             w.println(element);
    //         }
    //         System.out.println("Arquivo criado com sucesso!");
    //     } catch (IOException e) {
    //         System.out.println("Não foi possível criar o arquivo solicitado!");
    //     }
        
    // }
}
