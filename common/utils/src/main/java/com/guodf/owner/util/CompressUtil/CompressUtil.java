package com.guodf.owner.util.CompressUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class CompressUtil {

    /**
     * GZIP格式压缩,入参为需要压缩的二进制数据
     * @param bytes
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream GZIPCompress(byte[] bytes) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(bytes);
        gzipOutputStream.close();
        return byteArrayOutputStream;
    }
    /**
     * GZIP格式压缩,入参为输入流程
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static ByteArrayOutputStream GZIPCompress(InputStream inputStream) throws IOException {
        int count = inputStream.available();
        byte[] bytes = new byte[count];
        inputStream.read(bytes);
        return GZIPCompress(bytes);
    }


    /**
     * 根据字节流信息生成ZIP压缩文件
     * @param bytes 字节流
     * @param resultFile 生成的压缩文件名
     * @param targetName
     * @throws IOException
     */
    public static void ZIPCompress(byte[] bytes,File resultFile,String targetName) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(resultFile));
        zipOutputStream.putNextEntry(new ZipEntry(targetName));
        zipOutputStream.write(bytes);
        zipOutputStream.close();
    }

    /**
     * 根据目标文件<code>targetFile<code/> 进行压缩,生成<code>resultFile<code/>
     * @param targetFile
     * @param resultFile
     * @throws IOException
     */
    public static void ZIPCompress(File targetFile,File resultFile) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(targetFile);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        ZIPCompress(bytes,resultFile,targetFile.getName());
    }

    public static File ZIPCompress(File targetFile) throws IOException {
        String resultCanonicalPath =  targetFile.getCanonicalPath();
        resultCanonicalPath = resultCanonicalPath.replace(targetFile.getName(),targetFile.getName().replaceAll("[.][^.]+$", "")+".zip");
        File resultFile = new File(resultCanonicalPath);
        ZIPCompress(targetFile,resultFile);
        return resultFile;
    }

    public static void unZIPCompress(File targetFile,File resultFile,String charsetName) throws IOException {
        ZipFile zipFile = new ZipFile(targetFile,Charset.forName(charsetName));
        for (Enumeration entries = zipFile.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zipFile.getInputStream(entry);
            OutputStream out = new FileOutputStream(resultFile);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }

    }


}

