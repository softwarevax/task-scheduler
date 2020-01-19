package org.platform.quartz.deploy.maven;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.util.List;

/**
 * @author twcao
 * @description maven导报命令
 * @project task-scheduler
 * @classname MavenHelper
 * @date 2020/1/13 15:22
 */
public class CompressHelper {

    public static final void compress(String destFileName, List<String> files) {
        if(CollectionUtils.isEmpty(files)) {
            return;
        }
        File[] file = new File[files.size()];
        for(int i = 0, len = file.length; i < len; i++) {
            file[i] = new File(files.get(i));
        }
        compress(destFileName, file);
    }

    public static final void compress(String destFileName, File... files) {
        try {
            File destFile = new File(destFileName);
            if (destFile.exists()) {
                FileUtils.forceDelete(destFile);
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(destFile);
                 BufferedOutputStream bufferedWriter = new BufferedOutputStream(fileOutputStream);
                 TarArchiveOutputStream tar = new TarArchiveOutputStream(bufferedWriter)) {

                tar.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);

                for (File file : files) {
                    addTarArchiveEntryToTarArchiveOutputStream(file, tar, "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addTarArchiveEntryToTarArchiveOutputStream(File file, TarArchiveOutputStream tar, String prefix) throws IOException {
        TarArchiveEntry entry = new TarArchiveEntry(file, prefix + File.separator + file.getName());
        if (file.isFile()) {
            entry.setSize(file.length());
            tar.putArchiveEntry(entry);
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream input = new BufferedInputStream(fileInputStream);) {
                IOUtils.copy(input, tar);
            }
            tar.closeArchiveEntry();
        } else {
            tar.putArchiveEntry(entry);
            tar.closeArchiveEntry();
            prefix += File.separator + file.getName();
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    addTarArchiveEntryToTarArchiveOutputStream(f, tar, prefix);
                }
            }
        }
    }
}
