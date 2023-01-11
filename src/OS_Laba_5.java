import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.util.Objects;
import java.util.Scanner;


public class OS_Laba_5 {
    static int razmer = 0;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        try {
            Files.createDirectory(Path.of("D:\\OS_Laba_5\\recorses\\rec"));
            Files.createFile(Path.of("D:\\OS_Laba_5\\recorses\\rec\\file"));
        } catch (IOException e) {}

        File file = new File("D:\\OS_Laba_5\\recorses\\rec\\file");
        File dir = new File("D:\\OS_Laba_5\\recorses\\rec");
        File file1 = new File("D:\\OS_Laba_5\\recorses\\read");

        Node[] files = new Node[100];

        String[][] dirrects = new String[10][10];
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                dirrects[i][j] = "";
            }
        dirrects[0][0] = dir.getName();
        dirrects[0][1] = file.getName();

        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер блока: ");
        razmer = in.nextInt();

        int m, INDEX = 0;
        files[INDEX] = new Node();
        index++;
        Node.initializing(files[INDEX], "D:\\OS_Laba_5\\recorses\\rec\\file", razmer);

        for (;;) {
            System.out.println("\nДля выполнения следующих операций введите следующие значения: " + "\n" +
                    "Выделить блоки (1),\nвыделить ряд блоков (2),\nосвободить ранее выделенные блоки (3)," + "\n" +
                    "запись данных в указанные блоки(4),\nчтение данных из указанных блоков в файл(5)," + "\n" +
                    "получить данные о состоянии блочного пространства(6),\nвернуть последнее внесенное изменеие в пространстве(7)," + "\n" +
                    "для выхода (0)." + "\n" +
                    "создать каталог или файл (8)" + "\n" +
                    "удалить каталог или файл (9)");

            int n = in.nextInt();
            if (n == 0) {
                System.out.println("\nДо свидания!!!!!!");
                break;
            }

            if (n == 1) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                System.out.print("\nВведите количество блоков: ");
                n = in.nextInt();
                Node.block_selection(files[INDEX], n);
                continue;
            }

            if (n == 2) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                System.out.print("\nВведите количество: ");
                n = in.nextInt();
                int[] mass = new int[n];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < n; i++)
                    mass[i] = in.nextInt();
                Node.block_selection(files[INDEX], mass);
                continue;
            }

            if (n == 3) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                System.out.print("\nВведите количество: ");
                m = in.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                Node.block_release(files[INDEX], mass);
                continue;
            }

            if (n == 4) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                Scanner inn = new Scanner(System.in);
                Scanner ins = new Scanner(System.in);
                System.out.print("\nВведите количество: ");
                m = inn.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                String[] messege = new String[m];
                System.out.print("\nВведите ряд сообщений: ");
                for (int i = 0; i  < m; i++)
                    messege[i] = ins.nextLine();
                Node.set_messeges(files[INDEX], messege, mass);
                continue;
            }

            if (n == 5) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                System.out.print("\nВведите количество: ");
                m = in.nextInt();
                int[] mass = new int[m];
                System.out.print("\nВведите ряд блоков: ");
                for (int i = 0; i < m; i++)
                    mass[i] = in.nextInt();
                Node.read(files[INDEX], file1, mass);
                continue;
            }

            if (n == 6) {
                INDEX = coutn_index(dirrects);
                Node.help(files[INDEX]);
                Node.inf_get(files[INDEX]);
                continue;
            }

            if (n == 7) {
                INDEX = coutn_index(dirrects);
                Node.return_kash(files[INDEX]);
            }

            if (n == 8) {
                Scanner ii = new Scanner(System.in);
                m = ii.nextInt();
                Scanner ins = new Scanner(System.in);
                if (m == 0) {
                    String d = ins.nextLine();
                    try {
                        Files.createDirectory(Path.of(d));
                    } catch (IOException e) {
                        System.out.println("Попробуйте снова!");
                        d = ins.nextLine();
                        Files.createDirectory(Path.of(d));
                    }
                    File dir1 = new File(d);
                    for (int i = 0; i < 10; i++) {
                        if (Objects.equals(dirrects[i][0], "")) {
                            dirrects[i][0] = dir1.getName();
                            break;
                        }
                    }
                }
                if (m == 1) {
                    String F = ins.nextLine();
                    try {
                        Files.createFile(Path.of(F));
                    } catch (IOException e) {
                        System.out.println("Попробуйте снова!");
                        F = ins.nextLine();
                        Files.createFile(Path.of(F));
                    }
                    File file0 = new File(F);
                    File help = file0.getParentFile();
                    for (int i = 0; i < 10; i++) {
                        if (Objects.equals(dirrects[i][0], help.getName())) {
                            for (int j = 1; j < 10; j++) {
                                if (Objects.equals(dirrects[i][j], "")) {
                                    dirrects[i][j] = file0.getName();
                                    files[index] = new Node();
                                    Node.initializing(files[index], F, razmer);
                                    index++;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }

            if (n == 9) {
                Scanner ii = new Scanner(System.in);
                m = ii.nextInt();
                Scanner ins = new Scanner(System.in);
                if (m == 0) {
                    String d = ins.nextLine();
                    File dir0 = new File(d);
                    int ot = 1, i = 0;
                    while (i < 10) {
                        if (Objects.equals(dirrects[i][0], dir0.getName())) {
                            for (int j = 1; j < 10; j++)
                                if (!Objects.equals(dirrects[i][j], "")) {
                                    System.out.println("Уверены?");
                                    Scanner otv = new Scanner(System.in);
                                    ot = otv.nextInt();
                                    break;
                                }
                            if (ot == 0)
                                i = 9;
                            if (ot == 1) {
                                for (int j = 1; j < 10; j++) {
                                    if (!Objects.equals(dirrects[i][j], "")) {
                                        String s = d + "\\" + dirrects[i][j];
                                        Path path = Path.of(s);
                                        Files.delete(path);
                                        dirrects[i][j] = "";
                                    }
                                }
                                dirrects[i][0] = "";
                                Path path = Path.of(d);
                                Files.delete(path);;
                                i = 9;
                            }
                        }
                        i++;
                    }
                }
                if (m == 1) {
                    String F = ins.nextLine();
                    boolean h_o_n = Node.have_or_not(files[INDEX]);
                    if (!h_o_n) {
                        System.out.println("Уверены?");
                        Scanner otv = new Scanner(System.in);
                        int ot = otv.nextInt();
                        if (ot == 1) {
                            Path path = Path.of(F);
                            Files.delete(path);
                        }
                    }
                    else {
                        Path path = Path.of(F);
                        Files.delete(path);
                    }
                }
            }
        }
    }

    public static int coutn_index (String[][] dirrects) {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 10; i++)
            for (int j = 0;j <  10; j++) {
                if (!Objects.equals(dirrects[i][j], "") & !Objects.equals(dirrects[i][j], "null")) {
                    if (j == 0)
                        System.out.println(i + ": " + dirrects[i][j]);
                    else
                        System.out.println("    " + j + ": " + dirrects[i][j]);
                }
            }
        int n = in.nextInt();
        int m = in.nextInt();
        int INDEX = 0;
        int i = 0;
        while (i < 10) {
            for (int j = 1; j < 10; j++) {
                if (i == n & j == m) {
                    i = 10;
                    break;
                }
                if (!Objects.equals(dirrects[i][j], ""))
                    INDEX++;
            }
            i++;
        }
        return INDEX;
    }

}

