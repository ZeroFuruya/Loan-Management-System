package e2p.icotp.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class FileUtil {
    public static final String FS = FileSystems.getDefault().getSeparator();
    public static final String ROOT = System.getenv("APPDATA") + FS + "@thesisManager" + FS;
    public static final String DATA_DIR = ROOT + ".data" + FS;
    public static final String CUSTOM_DIR = "C:" + FS + "Program Files" + FS + "Zephyr Loan Management System" + FS
            + "data" + FS;

    public static final String TEMP_DIR = ROOT + ".temp" + FS;
    public static final String CACHE_DIR = ROOT + ".cache" + FS;
    public static final String SERVER_DIR = ROOT + ".server" + FS;

    public static void init_appdata() throws Exception {
        try {
            create_dir(ROOT);

            // data dir
            create_dir(DATA_DIR);
            // temp dir
            create_dir(TEMP_DIR);
            hide(TEMP_DIR);
            // server dir
            create_dir(SERVER_DIR);
            hide(SERVER_DIR);
            // cache dir
            System.gc();
            FileUtils.deleteDirectory(new File(CACHE_DIR));
            create_dir(CACHE_DIR);
            hide(CACHE_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void create_dir(String dir_path) throws Exception {
        if (dir_path == null || dir_path.isEmpty() || dir_path.length() == 0) {
            throw new Exception("No Such Directory");
        }

        File dir = new File(dir_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void hide(String pathString) {
        Path path = FileSystems.getDefault().getPath(pathString);
        try {
            Files.setAttribute(path, "dos:hidden", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy_to_destination(File src, File destination) throws IOException {
        int buffer_size;
        byte[] buffer = new byte[3072];

        try {
            InputStream iStream = new FileInputStream(src);
            OutputStream oStream = new FileOutputStream(destination);

            while ((buffer_size = iStream.read(buffer)) > 0) {
                oStream.write(buffer, 0, buffer_size);
            }
            iStream.close();
            oStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public File name_checker(File dir, String filename) {
        File[] files = dir.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().equals(filename)) {
                    return file;
                }
            }
        }
        return null;
    }

    public static void convert_png_to_destination(File src, File destination) throws IOException {
        ByteArrayInputStream byteIS = new ByteArrayInputStream(FileUtils.readFileToByteArray(src));
        BufferedImage buffered_img = ImageIO.read(byteIS);

        ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
        ImageIO.write(buffered_img, "png", byteOS);

        OutputStream out_stream = new FileOutputStream(destination);
        byteOS.writeTo(out_stream);

        byteIS.close();
        byteOS.close();
        out_stream.close();
    }
}
