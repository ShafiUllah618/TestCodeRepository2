package com.infosys.iFlow;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

 

public class ZipFile {
    public static void main(String[] args) throws IOException {
        File sourceLocation = new File("CPIFLowDeployment.iflw"); //$NON-NLS-1$
        File targetLocation = new File(
                Messages.getString("ZipFile.targetLocationIFlwFIle")); //$NON-NLS-1$
        System.out.println(targetLocation.getAbsolutePath());
        
        System.out.println(targetLocation.isDirectory());
        FileUtils.copyFileToDirectory(sourceLocation, targetLocation);
        String source = Messages.getString("ZipFile.LocationToBeZippied"); //$NON-NLS-1$
        String targetZip = Messages.getString("ZipFile.LocationToPlaceZipFile"); //$NON-NLS-1$
        ZipFile zip = new ZipFile();
        zip.pack(source, targetZip);
     
    }

 

    public void pack(String sourceDirPath, String zipFilePath) throws IOException {
        Path p = Files.createFile(Paths.get(zipFilePath));
        Path pp = Paths.get(sourceDirPath);
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p)); Stream<Path> paths = Files.walk(pp)) {
            paths.filter(path -> !Files.isDirectory(path)).forEach(path -> {
                ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                try {
                    zs.putNextEntry(zipEntry);
                    Files.copy(path, zs);
                    zs.closeEntry();
                } catch (IOException e) {
                    System.err.println(e);
                }
            });
        }
    }
    
}