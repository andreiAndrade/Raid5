import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TO DO
//* write disk0, disk1 and parity on real files (????)
//* check if the file was deleted to then recover it

public class Main {

    //region Attributes
    private static final String PATH = "C:\\RAID5\\";
    private static final String DISK0 = "Disk0";
    private static final String DISK1 = "Disk1";
    private static final String PARITY = "Parity";
    private static final String FILE0 = "File0.txt";
    private static final String FILE1 = "File1.txt";
    private static final String FILEPARITY = "FileParity.txt";
    private static final String FILERECOVERED = "Arquivo Recuperado.txt";
    private static PrintWriter w;
    //endregion

    //region PrivateMethods

    //Cria diretórios que representaram os disco 0, 1 e de paridade REFATORADO
    private static void createDisks(String... names){
        File disk;
        for (String name : names){
            disk = new File(PATH, name);
            if (!disk.exists()){
                if (!disk.mkdirs()) System.out.printf("%s não pode ser criado!\n", name);
                else System.out.printf("%s criado com sucesso!\n", name);
            }
        }
    }

    //Cria o arquivo original REFATORADO
    private static File createFileOriginal(){
        try {
            File file = new File(PATH + "Hino.txt");
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
            System.out.printf("Arquivo %s criado com sucesso!", file.getName());
            return file;
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }
        return null;
    }

    //Converte o arquivo em bytes REFATORADO
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

    //Grava os arquivos de bytes nos discos REFATORADO
    private static void saveFileWithRAID5(byte[] bytesArray){
        //variaveis locais
        boolean addFirstDisk = true;
        List<Byte> file0 = new ArrayList<>();
        List<Byte> file1 = new ArrayList<>();
        List<Byte> fileParity = new ArrayList<>();

        //preenche os discos com os arquivos de bytes
        for(byte element : bytesArray) {
            if (!addFirstDisk) file0.add(element);
            else file1.add(element);
            addFirstDisk = !addFirstDisk;
        }

        //Cria paridade
        for (int i = 0; i < file1.size(); i++){
            fileParity.add(
                    (i == file0.size()) ? file1.get(i) : (byte) (file1.get(i) - file0.get(i))
            );
        }

        fillBytesFile(file0, new File(PATH + DISK0, FILE0));
        fillBytesFile(file1, new File(PATH + DISK1, FILE1));
        fillBytesFile(fileParity, new File(PATH + PARITY, FILEPARITY));
    }

    //cria arquivo como byte. e chamado pelo saveFileWithRAID5 REFATORADO
    private static void fillBytesFile(List<Byte> bytesArray, File file){
        try {
            w = new PrintWriter(file);
            for (byte value : bytesArray) w.println(value);
            w.close();
            System.out.printf("Arquivo %s criado com sucesso!\n", file.getName());
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível criar o arquivo solicitado!");
        }
    }

    //Apaga um disco para teste de recuperação da paridade
    private static void deleteDisk(){

        Scanner sc = new Scanner(System.in);
        File disk0 = new File(PATH + DISK0);
        File disk1 = new File(PATH + DISK1);

        System.out.println("--Remover disco--");
        System.out.println("Escolha o disco:");
        System.out.println("0. Disk0;");
        System.out.println("1. Disk1.");

        System.out.printf("Disco: ");
        int option = sc.nextInt();
        switch (option){
            case 0:
                if (disk0.exists()) {
                    String[] contents = disk0.list();
                    for (String content : contents){
                        new File(disk0, content).delete();
                    }
                    if (!disk0.delete()) System.out.println("Disk0 não pode ser deletado!");
                    else System.out.println("Disk0 foi deletado com sucesso!");
                } else{
                    System.out.println("O disco solicitado não foi encontrado");
                }
                break;
            case 1:
                if (disk1.exists()) {
                    String[] contents = disk1.list();
                    for (String content : contents){
                        new File(disk1, content).delete();
                    }
                    if (!disk1.delete()) System.out.println("Disk1 não pode ser deletado!");
                    else System.out.println("Disk1 foi deletado com sucesso!");
                } else {
                    System.out.println("O disco solicitado não foi encontrado");
                }
                break;
        }

    }

    //Recupera o disco com o disco de paridade REFATORADO
    private static void findDiskCorrupted(){
        File pathDisk0 = new File(PATH + DISK0);
        File pathDisk1 = new File(PATH + DISK1);

        if (pathDisk0.exists() && pathDisk1.exists()){
            System.out.println("Nenhum disco esta corrompido!");
        } else if (!pathDisk0.exists() && !pathDisk1.exists()){
            System.out.println("Impossivel recuperar os arquivos. Os dois discos foram corrompidos!");
        } else if (!pathDisk0.exists()){
            recoveryDisk(DISK0, FILE0);
        } else {
            recoveryDisk(DISK1, FILE1);
        }
    }

    private static void recoveryDisk(String disk, String file){
        try {
            //recria disco apagado
            createDisks(disk);
            //recria arquivo apagado
            File recoveredFile = new File(PATH + disk, FILERECOVERED);

            Scanner readFile = new Scanner(new FileReader(new File(PATH + disk, file))).useDelimiter("\\n");
            Scanner readFileParity = new Scanner(new FileReader(new File(PATH + disk, FILEPARITY))).useDelimiter("\\n");

            byte byte0;
            byte byteParity;
            List<Byte> bytesArrayFile = new ArrayList<>();

            while (readFileParity.hasNext()) {
                byte0 = (readFile.hasNext()) ? Byte.parseByte(readFile.next().replace("\r", "")) : 0;
                byteParity = Byte.parseByte(readFileParity.next().replace("\r", ""));
                bytesArrayFile.add((file.equals(FILE0)) ? (byte) (byte0 - byteParity) : (byte) (byteParity + byte0));
            }

            fillBytesFile(bytesArrayFile, recoveredFile);
            System.out.println("Arquivo recuperado com sucesso!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Remonta os arquivo
    private static void remakeFile() {
        List<Byte> fullFile = new ArrayList<>();
        Scanner sc;
        byte[] fullFileArray;
        String[] contentDisk0 = new File(PATH + DISK0).list();
        String[] contentDisk1 = new File(PATH + DISK1).list();
        File file0 = new File(PATH + DISK0, contentDisk0[0]);
        File file1 = new File(PATH + DISK1, contentDisk1[0]);

        try {
            sc = new Scanner(file0);
            while (sc.hasNext()){
                fullFile.add(Byte.parseByte(sc.next()));
            }
            sc = new Scanner(file1);
            while (sc.hasNext()){
                fullFile.add(Byte.parseByte(sc.next()));
            }
            fullFileArray = new byte[fullFile.size()];
            for (int i = 0; i < fullFile.size(); i++){
                fullFileArray[i] = fullFile.get(i);
            }
            String s = new String(fullFileArray);
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public static void main(String[] args) throws FileNotFoundException {

        createDisks(DISK0, DISK1, PARITY);

        File fileOriginal = createFileOriginal();
        byte[] fileLikeByte = parseFileToByte(fileOriginal);
        saveFileWithRAID5(fileLikeByte);
        deleteDisk();
        findDiskCorrupted();
//        remakeFile();

 
    }
}

