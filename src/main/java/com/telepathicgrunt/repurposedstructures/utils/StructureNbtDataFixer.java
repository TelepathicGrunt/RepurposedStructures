package com.telepathicgrunt.repurposedstructures.utils;

import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.datafixer.Schemas;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.nbt.NbtIo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class StructureNbtDataFixer {


    public static void updateAllNbtFiles() throws IOException {
        String mainPath = "C:\\Users\\Admin\\Documents\\PersonalFun\\Minecraft stuff\\JavaCodeMods\\ModdingWorkspace\\RepurposedStructures-Fabric";
        String resourcePath = mainPath+"\\src\\main\\resources\\data";

        List<File> files = new ArrayList<>();
        setAllNbtFilesToList(resourcePath, files);
        for(File file : files){
            InputStream inputStream = new FileInputStream(file);

            File resultingFile = new File(mainPath+"//"+file.getAbsolutePath().split("resources\\\\")[1]);
            resultingFile.getParentFile().mkdirs();
            OutputStream outputStream = new FileOutputStream(resultingFile);

            CompoundTag newNBT = updateNbtCompound(inputStream);
            NbtIo.writeCompressed(newNBT, outputStream);
        }
    }

    //source: https://stackoverflow.com/a/14676464
    public static void setAllNbtFilesToList(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile() && file.getName().contains(".nbt")) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    setAllNbtFilesToList(file.getAbsolutePath(), files);
                }
            }
        }
    }


    public static CompoundTag updateNbtCompound(InputStream structureInputStream) throws IOException {
        CompoundTag compoundTag = NbtIo.readCompressed(structureInputStream);
        return NbtHelper.update(Schemas.getFixer(), DataFixTypes.STRUCTURE, compoundTag, compoundTag.getInt("DataVersion"), compoundTag.getInt("DataVersion"));
    }
}
